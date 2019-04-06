package com.example.demo.ActiveParking;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Column(name = "time")
    @NotFound(action = NotFoundAction.IGNORE)
    private Timestamp time;

    public boolean isParked(String username){
        return this.username == null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTime(){
        return time;
    }

    public void setTime(Timestamp time){
        this.time = time;
    }

}