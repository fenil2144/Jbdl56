package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhone(username);
    }

    public void create(UserCreateRequest userCreateRequest) throws JsonProcessingException {
        User user = userCreateRequest.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(UserConstants.USER_AUTHORITY);
        user = userRepository.save(user);

        //publish the event post user creation which will listened by consumers

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_USERID,user.getId());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_PHONE,user.getPhone());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY,user.getUserIdentifier());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE,user.getIdentifierValue());

        kafkaTemplate.send(CommonConstants.USER_CREATION_TOPIC,
                objectMapper.writeValueAsString(jsonObject));
    }
}
