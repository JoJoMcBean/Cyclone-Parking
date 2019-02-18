package com.example.demo.user_login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public class userService{

    private static List<user_login> allUsers;



    public static void addUser(user_login user){
        allUsers.add(user);
    }
}

