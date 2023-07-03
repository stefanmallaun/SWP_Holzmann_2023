package com.example.demo2.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DeleteForm { 
    @NotBlank
    @Email
    private String username;
}
