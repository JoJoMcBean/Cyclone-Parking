package com.example.demo.Messages;

import com.example.demo.DefaultUsers.DefaultUsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {


    @Autowired
    MessagesRepository messagesRepository;

    private final Logger logger = LoggerFactory.getLogger(DefaultUsersController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/messages")
    public List<Messages> getMessages() {
        return messagesRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addMessage")
    public String addMessage(@RequestBody Map<String, Object> messageToAdd) {

        logger.info(messageToAdd.toString());
        String username = messageToAdd.get("username").toString();
        String message = messageToAdd.get("message").toString();

        Instant instant = Instant.now();
        Long timeStampMillis = instant.toEpochMilli();

        messagesRepository.addMessage(username, message, timeStampMillis);

        return "message added";
    }
}
