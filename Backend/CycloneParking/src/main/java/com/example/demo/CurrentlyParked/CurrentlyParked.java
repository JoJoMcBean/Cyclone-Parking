package com.example.demo.CurrentlyParked;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Spot.class)
@Table(name = "currently_parked")
public class CurrentlyParked implements Serializable {

    @Column(name = "username")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;


    @Id
    @Column(name = "lotid")
    @JoinColumn(name = "lotid")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lotid;
    
    @Id
    @Column(name = "spotnum")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer spotnum;


    @Column(name = "license")
    @JoinColumn(name = "lotid")
    @NotFound(action = NotFoundAction.IGNORE)
    private String license;

    @Column(name = "timeparked")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long timeparked;

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

    public Integer getSpotnum() {
        return spotnum;
    }

    public void setSpotnum(Integer spotnum) {
        this.spotnum = spotnum;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getTimeparked() {
        return timeparked;
    }

    public void setTimeparked(Long timeparked) {
        this.timeparked = timeparked;
    }



}

class Spot implements Serializable{
    String lotid;
    Integer spotnum;
}
