package com.example.demo.user_login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
class user_loginController {

    @Autowired
    user_loginRepository user_loginRepository;

    private final Logger logger = LoggerFactory.getLogger(user_loginController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/add/user")
    public user_login createUser(@Valid @RequestBody user_login newUser) {
        return user_loginRepository.save(newUser);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<user_login> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<user_login> results = user_loginRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}")
    public Optional<user_login> findUserByUsername(@PathVariable("username") String username) {
        logger.info("Entered into Controller Layer");
        Optional<user_login> results = user_loginRepository.findById(username);
        return results;
    }

}
