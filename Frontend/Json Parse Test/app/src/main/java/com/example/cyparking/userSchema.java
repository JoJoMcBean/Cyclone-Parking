package com.example.cyparking;

public class userSchema {
    private String username;
    private String password;
    private String email;
    public userSchema(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
}
