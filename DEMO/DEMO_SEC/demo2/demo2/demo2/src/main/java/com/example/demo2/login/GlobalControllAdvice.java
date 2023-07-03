package com.example.demo2.login;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllAdvice {
@ExceptionHandler(DataAccessException.class)
public String dataAccessExceptionHandler(DataAccessException e, Model model) {
model.addAttribute("error", "Internal server error (DB): GlobalControllAdvice");
model.addAttribute("message", "DataAccessException occurred");
model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
return "error";}
@ExceptionHandler(Exception.class)
public String exceptionHandler(Exception e, Model model) {
model.addAttribute("error", "Internal server error: GlobalControllAdvice");
model.addAttribute("message", "Exception occurred");
model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
return "error";
}
}

