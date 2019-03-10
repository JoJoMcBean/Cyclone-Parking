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

    @RequestMapping(method = RequestMethod.POST, value = "/get/default")
    public String getUserInfoOnToken(String token){

        logger.info(token);

        if(token == null){
            return "null token";
        }
        /*
        Scanner s = new Scanner(token);
        String parsedToken = "";
        while(s.hasNext()){
            if(s.next().contains(":")){
                parsedToken = s.next();
            }
        }
        String finalParse = parsedToken.substring(1, parsedToken.length() - 1);
        logger.info(finalParse);
        */
        String userInfo = defaultUsersRepository.getUserInfoWithToken(token);
        return userInfo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/default")
    public DefaultUsers update(@Valid @RequestBody DefaultUsers updateUser){
        if(defaultUsersRepository.existsById(updateUser.getUsername())) {
            return defaultUsersRepository.save(updateUser);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/userinfo")
    public String updateInfo(String userInfo){
        String[] infoArray = userInfo.split(",");
        String password = infoArray[0];
        String email = infoArray[1];
        String license = infoArray[2];
        String cardNum = infoArray[3];
        String token = infoArray[4];

        String username = defaultUsersRepository.getUsernameFromToken(token);
        defaultUsersRepository.updateUserLoginInfo(password, email, token);
        defaultUsersRepository.updateDefaultUsersInfo(license, cardNum, username);

        return "Success";

    }





}
