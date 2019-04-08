package com.example.demo.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

@Autowired
private UserLoginRepository repo;


    public UserLogin getUser(String username){
        return repo.getUser(username);
    }

    public List<UserLogin> getAllUsers(){
        return repo.findAll();
    }

    public List<String> getUsernames() {
        return repo.getUsernames();
    }
}

