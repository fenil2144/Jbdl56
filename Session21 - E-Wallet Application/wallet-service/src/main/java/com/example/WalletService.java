package com.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;


    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC,groupId = "grp123")
    public void createWallet(String message) throws ParseException {

        JSONObject data = (JSONObject) new JSONParser().parse(message);

        Long userId = (Long) data.get(CommonConstants.USER_CREATION_TOPIC_USERID);
        String phone = (String) data.get(CommonConstants.USER_CREATION_TOPIC_PHONE);
        String identifierKey = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId).phone(phone).userIdentifier(UserIdentifier.valueOf(identifierKey))
                .identifierValue(identifierValue)
                .build();

        walletRepository.save(wallet);
    }

}
