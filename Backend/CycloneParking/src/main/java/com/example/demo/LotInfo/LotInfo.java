package com.example.demo.LotInfo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "lot_info")

public class LotInfo {
    @Id
    @GeneratedValue
    @Column(name = "lotid")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lotid;

    @Column(name = "totalspots")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer totalspots;

    @Column(name = "location")
    @NotFound(action = NotFoundAction.IGNORE)
    private String location;

    @Column(name = "cost")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cost;

    @Column(name = "hours")
    @NotFound(action = NotFoundAction.IGNORE)
    private String hours;

    public String getLotid() {
        return lotid;
    }

    public void setLotid(String lotid) {
        this.lotid = lotid;
    }

    public Integer getTotalspots() {
        return totalspots;
    }

    public void setTotalspots(Integer totalspots) {
        this.totalspots = totalspots;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
