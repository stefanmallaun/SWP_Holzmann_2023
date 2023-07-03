package com.example.demo2.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class StartController {

    @GetMapping(value={"/", "/start"})
    
    public String getStart(
        @ModelAttribute LoginForm form, 
        Model model) {
        return "start";
    }
    
}
