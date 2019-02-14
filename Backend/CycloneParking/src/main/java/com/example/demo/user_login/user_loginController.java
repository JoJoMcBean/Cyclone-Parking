package com.example.demo.user_login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
class user_loginController {

    @Autowired
    user_loginRepository user_loginRepository;

    private final Logger logger = LoggerFactory.getLogger(user_loginController.class);


    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<user_login> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<user_login> results = user_loginRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

}
