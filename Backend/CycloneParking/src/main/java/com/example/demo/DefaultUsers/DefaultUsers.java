package com.example.demo.DefaultUsers;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "default_users")
public class DefaultUsers {

    @Id
    @Column(name = "username")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    /*
    @Column(name = "netid")
    @NotFound(action = NotFoundAction.IGNORE)
    private String netid;
    */

    @Column(name = "license")
    @NotFound(action = NotFoundAction.IGNORE)
    private String license;


    @Column(name = "cardnum")
    @NotFound(action = NotFoundAction.IGNORE)
    private String cardnum;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
    public String getnetId() {
        return netid;
    }

    public void setnetId(String netid) {
        this.netid = netid;
    }
    */

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getcardNum() {
        return cardnum;
    }

    public void setcardNum(String cardNum) {
        this.cardnum = cardNum;
    }
}