package com.example.cyparking;

public class userSchema {
    private String username;
    private String password;
    private String userType;
    private String email;
    public userSchema(String username, String password, String userType, String email) {
        this.username = username;
        this.password = password;
        this.userType = userType;
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
