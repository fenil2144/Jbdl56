package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/transact")
    public String initiateTransaction(@RequestParam("receiver") String receiver,
                                      @RequestParam("message") String message,
                                      @RequestParam("amount") Double amount) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
//        logger.info("Password: "+user.getPassword()); -- prints null
        return transactionService.initiateTransaction(user.getUsername(),receiver,message,amount);
    }
}
