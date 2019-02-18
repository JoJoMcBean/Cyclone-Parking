package com.example.cyparking;

public class userSchema {
    private String username;
    private String password;
    private String email;
    private String userType;
    public userSchema(String userType, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.userType = username;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getUserType() {
        return userType;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
