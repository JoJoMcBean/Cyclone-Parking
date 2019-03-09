package com.example.demo.ActiveParking;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "active_parking")
public class ActiveParking {

    @Id
    @Column(name = "username")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    @Column(name = "license")
    @NotFound(action = NotFoundAction.IGNORE)
    private String license;


    @Column(name = "stminute")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardNum;

    @Column(name = "sthour")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardNum;

    @Column(name = "stday")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardNum;

    @Column(name = "stmonth")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardNum;

    @Column(name = "styear")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardNum;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNet_id() {
        return net_id;
    }

    public void setNet_id(String net_id) {
        this.net_id = net_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}