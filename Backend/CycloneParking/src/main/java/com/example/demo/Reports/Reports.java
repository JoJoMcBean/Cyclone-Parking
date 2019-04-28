package com.example.demo.Reports;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reports")
public class Reports implements Serializable {



    @Id
    @Column(name = "rid")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer rid;

    @Column(name = "filedby")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String filedby;


    @Column(name = "lot")
    @NotFound(action = NotFoundAction.IGNORE)
    private String lot;

    @Column(name = "spot")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer spot;


    @Column(name = "description")
    @NotFound(action = NotFoundAction.IGNORE)
    private String description;

    @Column(name = "filetime")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long filetime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getFiledby() {
        return filedby;
    }

    public void setFiledby(String filedby) {
        this.filedby = filedby;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getSpot() {
        return spot;
    }

    public void setSpot(Integer spot) {
        this.spot = spot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFiletime() {
        return filetime;
    }

    public void setFiletime(Long filetime) {
        this.filetime = filetime;
    }
}
