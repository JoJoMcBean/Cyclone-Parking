package com.example.demo.Messages;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @Column(name = "mid")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer mid;


    @Column(name = "username")
    @JoinColumn(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;


    @Column(name = "message")
    @NotFound(action = NotFoundAction.IGNORE)
    private String message;

    @Column(name = "timestamp")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long timestamp;


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
