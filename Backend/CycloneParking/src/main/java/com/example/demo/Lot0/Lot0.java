package com.example.demo.Lot0;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lot0")


public class Lot0 {

    @Id
    @Column(name = "spotNum")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer spotNum;

    @Column(name = "filled")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean filled;

    public int getSpotNum(){
        return spotNum;
    }
    public boolean getIsFilled(){
        return filled;
    }
    public void setSpotNum(Integer spotNum){
        this.spotNum = spotNum;
    }
    public void setfilled(boolean filled){
        this.filled = filled;
    }
}
