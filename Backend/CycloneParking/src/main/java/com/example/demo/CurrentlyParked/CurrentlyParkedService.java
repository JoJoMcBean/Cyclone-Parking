package com.example.demo.CurrentlyParked;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentlyParkedService{

    @Autowired
    private CurrentlyParkedRepository repo;


    public void addParkedSpot(String username, String lot, Integer spot, String license, Long time){
        repo.addParkedSpot(username, lot, spot, license, time);
    }

    public String getLicenseFromUsername(String username){
        return repo.getLicenseWithToken(username);
    }


}

