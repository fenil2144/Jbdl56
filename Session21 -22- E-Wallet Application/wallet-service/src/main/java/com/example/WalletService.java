package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static Logger logger= LoggerFactory.getLogger(WalletService.class);

    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC,groupId = "grp123")
    public void createWallet(String message) throws ParseException {

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        Long userId = (Long) data.get(CommonConstants.USER_CREATION_TOPIC_USERID);
        String phone = (String) data.get(CommonConstants.USER_CREATION_TOPIC_PHONE);
        String identifierKey = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId).phone(phone).userIdentifier(UserIdentifier.valueOf(identifierKey))
                .identifierValue(identifierValue).balance(10.0)
                .build();

        walletRepository.save(wallet);
    }

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATE_TOPIC,groupId = "EWallet_Grp")
    public void updateWalletForTransaction(String message) throws ParseException, JsonProcessingException {

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String sender = (String) data.get("sender");
        String receiver = (String) data.get("receiver");
        Double amount = (Double) data.get("amount");
        String transactionId = (String) data.get("transactionId");

        logger.info("Validating Sender's Wallet balance: Sender- {}, receiver - {}," +
                "amount- {},transactionId-{}",sender,receiver,amount,transactionId);

        Wallet senderWallet = walletRepository.findByPhone(sender);
        Wallet receiverWallet  = walletRepository.findByPhone(receiver);

        //publish the event after validating and updating wallets
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender",sender);
        jsonObject.put("receiver",receiver);
        jsonObject.put("amount",amount);
        jsonObject.put("transactionId",transactionId);

        if(senderWallet== null || receiverWallet== null ||senderWallet.getBalance()<=amount){
            jsonObject.put("walletUpdateStatus",WalletUpdateStatus.FAILED);
        }else{
            walletRepository.updateWallet(sender,0-amount);
            walletRepository.updateWallet(receiver,amount);
            jsonObject.put("walletUpdateStatus",WalletUpdateStatus.SUCCESS);
        }

        kafkaTemplate.send(CommonConstants.WALLET_UPDATED_TOPIC,
                objectMapper.writeValueAsString(jsonObject));
    }

}
