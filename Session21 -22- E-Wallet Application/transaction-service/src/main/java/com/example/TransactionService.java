package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService implements UserDetailsService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject requestUser = getUserFromUserService(username);

        List<GrantedAuthority> authorities;
        List<LinkedHashMap<String,String>> reqAuthorities = (List<LinkedHashMap<String,String>>)
                requestUser.get("authorities");
        authorities = reqAuthorities.stream().map(x -> x.get("authority"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User((String) requestUser.get("username"),(String)requestUser.get("password")
                ,authorities);
    }

    private JSONObject getUserFromUserService(String username){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("txn_service","txn123");
        HttpEntity request = new HttpEntity(httpHeaders);
        return restTemplate.exchange("http://localhost:5000/admin/user/"+username, HttpMethod.GET,
                request,JSONObject.class).getBody();
    }

    public String initiateTransaction(String sender,String receiver,String message, Double amount) throws JsonProcessingException {
        Transaction transaction = Transaction.builder()
                .sender(sender).receiver(receiver).message(message)
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .amount(amount)
                .build();

        transactionRepository.save(transaction);

        //publish the event after initiating the transaction which will be listened by consumers
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender",sender);
        jsonObject.put("receiver",receiver);
        jsonObject.put("amount",amount);
        jsonObject.put("transactionId",transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATE_TOPIC,
                objectMapper.writeValueAsString(jsonObject));

        return transaction.getTransactionId();
    }

    @KafkaListener(topics=CommonConstants.WALLET_UPDATED_TOPIC,groupId = "EWallet_Group")
    public void updateTransaction(String message) throws JsonProcessingException, ParseException {
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String sender = (String) data.get("sender");
        String receiver = (String) data.get("receiver");
        Double amount = (Double) data.get("amount");
        String transactionId = (String) data.get("transactionId");

        WalletUpdateStatus walletUpdateStatus = WalletUpdateStatus.valueOf((String) data.get("walletUpdateStatus"));

        JSONObject senderObj = getUserFromUserService(sender);
        String senderEmail = (String) senderObj.get("email");

        String receiverEmail = null;
        if(walletUpdateStatus == WalletUpdateStatus.SUCCESS){
            JSONObject receiverObject = getUserFromUserService(receiver);
            receiverEmail = (String) receiverObject.get("email");
            transactionRepository.updateTransaction(transactionId,TransactionStatus.SUCCESS);
        }else{
            transactionRepository.updateTransaction(transactionId,TransactionStatus.FAILED);
        }

        String senderMsg = "Hi, Your Transaction with id "+transactionId+ "got "+walletUpdateStatus;

        JSONObject senderEmailObj = new JSONObject();
        senderEmailObj.put("email",senderEmail);
        senderEmailObj.put("msg",senderMsg);

        kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETION_TOPIC,objectMapper.writeValueAsString(senderEmailObj));

        if(walletUpdateStatus == WalletUpdateStatus.SUCCESS){
            String receivermsg = "Hi, you have received Rs. "+amount+" from "+sender+
                    " in your wallet linked with phone "+receiver;
            JSONObject receiverEmailObj = new JSONObject();
            receiverEmailObj.put("email",receiverEmail);
            receiverEmailObj.put("msg",receivermsg);

            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETION_TOPIC,objectMapper.writeValueAsString(receiverEmailObj));

        }
    }
}
