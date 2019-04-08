package com.example.cyparking.parkinglog;

import java.util.ArrayList;

public class ParkingLog {

    private String spaceID;
    private String lotID;
    private String username;
    private String timeStart;
    private String timeEnd;
    private String paid;

    public ParkingLog(String spaceID, String lotID, String username, String timeStart, String timeEnd, String paid) {
        this.spaceID = spaceID;
        this.lotID = lotID;
        this.username = username;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.paid = paid;
    }

    public String getSpaceID() {
        return spaceID;
    }

    public String getLotID() {
        return lotID;
    }

    public String getUsername() {
        return username;
    }

    public String getTimeStart() { return timeStart; }

    public String getTimeEnd() { return timeEnd; }

    public String getPaid() {return paid;}

}
