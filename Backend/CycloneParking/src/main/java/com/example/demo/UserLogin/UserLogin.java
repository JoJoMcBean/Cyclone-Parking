package com.example.demo.UserLogin;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserLogin")
public class UserLogin {

    @Id
    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    @Column(name = "password")
    @NotFound(action = NotFoundAction.IGNORE)
    private String password;

    @Column(name = "user_type")
    @NotFound(action = NotFoundAction.IGNORE)
    private String user_type;


    @Column(name = "email")
    @NotFound(action = NotFoundAction.IGNORE)
    private String email;

    @Column(name = "token")
    @NotFound(action = NotFoundAction.IGNORE)
    private String token;

    public UserLogin(){}
    public UserLogin(String username, String password, String user_type, String email, String token) {
        this.username = username;
        this.password = password;
        this.user_type = user_type;
        this.email = email;
        this.token = token;
    }

    public String getToken(){return token;}

    public void setToken(String token){this.token = token;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNew() {
        return this.username == null;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUser_type()
    {
        return this.user_type;
    }

    public void setUser_type(String user_type) {

        this.user_type = user_type;
    }

    public String getEmail() {
        return this.email;
    }

        public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("username", this.getUsername())
                .append("password", this.getPassword())
                .append("user_type", this.getUser_type())
                .append("email", this.getEmail()).toString();
    }

}
