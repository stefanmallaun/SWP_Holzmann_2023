package com.example.demo2.login;

import java.sql.Date;

import lombok.Data;
@Data
public class Member {
    private String email;
    private String password;
    private Date birthday;
    private String role;
}
