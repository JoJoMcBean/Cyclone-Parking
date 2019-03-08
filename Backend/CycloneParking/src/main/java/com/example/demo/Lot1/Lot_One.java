package com.example.demo.Lot1;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "lot_one")


public class Lot_One {

    @Id
    @GeneratedValue
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer spotnum;

    @Column(name = "isfilled")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean filled;

    public Integer getSpotNum(){
        return spotnum;
    }
    public boolean getisFilled(){
        return filled;
    }
    public void setspotNum(Integer spotNum){
        this.spotnum = spotNum;
    }
    public void setisFilled(boolean filled){
        this.filled = filled;
    }
}
