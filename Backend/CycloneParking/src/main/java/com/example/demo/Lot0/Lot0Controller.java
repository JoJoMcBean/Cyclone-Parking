package com.example.demo.Lot0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Lot0Controller {

    @Autowired
    Lot0Repository Lot0Repository;

    private final Logger logger = LoggerFactory.getLogger(Lot0Controller.class);

    @RequestMapping(method = RequestMethod.GET, path = "/lot0")
    public List<Lot0> getLotInfo() {
        logger.info("Entered into Controller Layer");
        List<Lot0> results = Lot0Repository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
}
