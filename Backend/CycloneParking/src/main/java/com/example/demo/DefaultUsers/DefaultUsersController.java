package com.example.demo.DefaultUsers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Scanner;

@RestController
public class DefaultUsersController {

    @Autowired
    DefaultUsersRepository defaultUsersRepository;

    private final Logger logger = LoggerFactory.getLogger(DefaultUsersController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/defaultusers")
    public List<DefaultUsers> getDefaultUsers() {
        logger.info("Entered into Controller Layer");
        List<DefaultUsers> results = defaultUsersRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add/default")
    public DefaultUsers createUser(@Valid @RequestBody DefaultUsers user) {

        if(!defaultUsersRepository.existsById(user.getUsername())) {
            return defaultUsersRepository.save(user);
        }
        return null;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/default")
    public String getUserOnToken(@RequestBody String token){
        logger.info(token);
        Scanner s = new Scanner(token);
        String parsedToken = "";
        while(s.hasNext()){
            if(s.next().contains(":")){
                parsedToken = s.next();
            }
        }
        String finalParse = parsedToken.substring(1, parsedToken.length() - 1);
        logger.info(finalParse);
        String userInfo = defaultUsersRepository.getUserWithToken(finalParse);
        s.close();
        return userInfo;
    }




}
