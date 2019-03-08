package com.example.demo.user_login;

import java.util.List;

public class userService{

    private static List<UserLogin> allUsers;



    public static void addUser(UserLogin user){
        allUsers.add(user);
    }
}

