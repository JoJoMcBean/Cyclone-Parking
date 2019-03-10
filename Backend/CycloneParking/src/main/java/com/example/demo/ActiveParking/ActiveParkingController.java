package com.example.demo.ActiveParking;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@RestController
public class ActiveParkingController {

    @Autowired
    ActiveParkingRepository activeParkingRepository;

    private final Logger logger = LoggerFactory.getLogger(ActiveParkingController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/add/time")
    public ActiveParking start(@Valid @RequestBody ActiveParking time){
        if(!activeParkingRepository.existsById(time.getUsername()))
            return activeParkingRepository.save(time);
        else
            return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/set")
    public ActiveParking setStart(@Valid @RequestBody ActiveParking time){
        if(activeParkingRepository.existsById(time.getUsername()))
            return activeParkingRepository.save(time);
        else
            return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/get")
    public ActiveParking getStart(@Valid @RequestBody ActiveParking time){
        if(activeParkingRepository.existsById(time.getUsername()))
            return activeParkingRepository.findByUsername(user.getUsername);
        else
            return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public String delete(String user){
        if(activeParkingRepository.existsById(user))
            ActiveParking time = activeParkingRepository.findByUsername(user);
            activeParkingRepository.delete(user);
            return "Pass";
        else
            return "Fail";
    }


}