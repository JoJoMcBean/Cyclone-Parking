package com.example.restapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Autowired
    @Qualifier("upper")
    TextWriter upperCase;

    @Autowired
    @Qualifier("fancy")
    TextWriter fancy;

    @RequestMapping("/")
    public String index(){

        return upperCase.writeText("Hello World") + "\n" + fancy.writeText("Hello World");

    }
}
