package com.example.demo2.login;

import java.util.Date;
import lombok.*;
import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String username;


    private String password;


    private String firstname;


    private String lastname;

    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
