package com.example.cyparking.spin;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class User_Info {
    private String userName;
    private String passWord;
    private ArrayList<String> carLiscencePlate;
    private com.example.cyparking.spin.UserType type;


    public User_Info(String userName, String passWord, String carLiscencePlate, com.example.cyparking.spin.UserType type)
    {
        this.userName = userName;
        this.passWord = passWord;
        this.carLiscencePlate = new ArrayList<>();
        this.type = type;


    }

    public String getCarLiscencePlate( int whichCar) {
        return  carLiscencePlate.get(whichCar);
    }

    public String getUserName(){
        return userName;
    }

    public void setCarLiscencePlate(String carLiscencePlate) {
        this.carLiscencePlate.add(carLiscencePlate);
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }


    @NonNull
    @Override
    public String toString() {
        String result = "User: " + this.getUserName() +"\n" +
                        "Password: " + this.getPassWord() + "\n" +
                        "Liscence Plate: " +this.getCarLiscencePlate(0) + "\n";

        return result;
    }
}
