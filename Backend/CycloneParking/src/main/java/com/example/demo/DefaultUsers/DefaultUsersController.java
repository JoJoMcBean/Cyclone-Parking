package com.example.demo.DefaultUsers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
