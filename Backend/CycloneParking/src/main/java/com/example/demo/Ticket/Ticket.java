package com.example.demo.Ticket;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(LicenseDate.class)
@Table(name = "Ticket")
public class Ticket implements Serializable {

    public Ticket(){
    }
    public Ticket(String license, String make, String color, String type, String violation, String comment, Long date, Boolean paid, String amount){
        this.license = license;
        this.make = make;
        this.color = color;
        this.type = type;
        this.violation = violation;
        this.comment = comment;
        this.date = date;
        this.paid = paid;
        this.amount = amount;
    }


    @Id
    @Column(name = "license")
    @NotFound(action = NotFoundAction.IGNORE)
    private String license;

    @Column(name = "make")
    @NotFound(action = NotFoundAction.IGNORE)
    private String make;

    @Column(name = "color")
    @NotFound(action = NotFoundAction.IGNORE)
    private String color;

    @Column(name = "type")
    @NotFound(action = NotFoundAction.IGNORE)
    private String type;

    @Column(name = "violation")
    @NotFound(action = NotFoundAction.IGNORE)
    private String violation;

    @Column(name = "comment")
    @NotFound(action = NotFoundAction.IGNORE)
    private String comment;
    
    @Id
    @Column(name = "date")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long date;

    @Column(name = "paid")
    @NotFound(action = NotFoundAction.IGNORE)
    private Boolean paid;

    @Column(name = "amount")
    @NotFound(action = NotFoundAction.IGNORE)
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

class LicenseDate implements Serializable{
    String license;
    Long date;
}
