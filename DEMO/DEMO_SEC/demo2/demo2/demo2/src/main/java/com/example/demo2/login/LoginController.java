package com.example.demo2.login;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo2.Models.RepositoryPerson;
//import com.example.demo2.Models.personService;

import io.micrometer.core.ipc.http.HttpSender.Request;

@ComponentScan("AOP")
@Controller
public class LoginController {

    @Autowired
    private RepositoryPerson rep;

    /**
     * Method for GET request of login screen.
     */
    @GetMapping(value={"/login"})
    // "@ModelAttribute LoginForm form" hinzufügen
    public String getLogin(
        @ModelAttribute LoginForm form, 
        Model model) {
        // template name returnieren
        return "login";
    }
}