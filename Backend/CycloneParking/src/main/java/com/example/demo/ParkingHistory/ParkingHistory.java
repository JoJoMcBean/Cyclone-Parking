package com.example.demo.ParkingHistory;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(UserTime.class)
@Table(name = "parking_history")
public class ParkingHistory implements Serializable {

    @Id
    @Column(name = "username")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;


    @Column(name = "lotid")
    @JoinColumn(name = "lotid")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lotid;

    @Id
    @Column(name = "timestart")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long timestart;

    @Column(name = "timeend")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long timeend;

    @Column(name = "paid")
    @NotFound(action = NotFoundAction.IGNORE)
    private Double paid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLotid() {
        return lotid;
    }

    public void setLotid(String lotid) {
        this.lotid = lotid;
    }

    public Long getTimestart() {
        return timestart;
    }

    public void setTimestart(Long timestart) {
        this.timestart = timestart;
    }

    public Long getTimeend() {
        return timeend;
    }

    public void setTimeend(Long timeend) {
        this.timeend = timeend;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }
}
class UserTime implements Serializable{
    String username;
    long timestart;
}