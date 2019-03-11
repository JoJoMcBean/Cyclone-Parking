package com.example.cyparking.parkinglog;

import java.util.ArrayList;

public class ParkingLog {

    private String id;
    private String location;

    public ParkingLog(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

}
