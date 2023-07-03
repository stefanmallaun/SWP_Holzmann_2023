package com.example.demo2.login;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo2.Models.RepositoryPerson;

//import com.example.demo2.Models.personService;

@Controller
public class RegisterController 
{
    
    @Autowired
    private RepositoryPerson rep;


    /**
     * Method for GET request of user registration screen.
     */
    @GetMapping("/register")
    public String getRegister(
        @ModelAttribute RegisterForm form, Model model) {
        // Screen transition to signup.html
        return "register";
    }

    /**
     * Method for POST request of user registration screen.
     */
    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Validated RegisterForm form, BindingResult bindingResult, Model model) 
    {

            if ( bindingResult.hasErrors() ) 
            {
                // return to the user login screen
                // Ausgabe auf der Konsole
                System.err.println("Binding has Errors:" + form); 
                // Eigene Fehlermeldung anzeigen
                model.addAttribute("registererror", "Fehlermeldung"); 
                // Rückkehr zum Registerscreen mit Fehlerinfo
                return getRegister(form, model);
            }   
        // check the form input
        System.out.println(form);    
        // redirect to login.html
        Person person = new Person();
        person.setUsername(form.getUsername());
        person.setPassword(form.getPassword());
        person.setFirstname(form.getFirstname());
        person.setLastname(form.getLastname());
        int p = rep.insertOne(person);
        if(p == 0)
        {
            System.err.println("Password has Errors:" + form);

        }
        // template
        return "redirect:/login";
    }
}