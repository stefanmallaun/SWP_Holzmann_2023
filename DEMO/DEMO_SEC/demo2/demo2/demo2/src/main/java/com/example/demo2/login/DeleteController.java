package com.example.demo2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo2.Models.RepositoryPerson;

@Controller
public class DeleteController
{
    
    @Autowired
    private RepositoryPerson rep;


    /**
     * Method for GET request of user registration screen.
     */
    @GetMapping("/delete")
    public String getDelete(
        @ModelAttribute DeleteForm form, Model model) {
        // Screen transition to signup.html
        return "delete";
    }

    /**
     * Method for POST request of user registration screen.
     */
    @PostMapping("/delete")
    public String postDelete(@ModelAttribute @Validated DeleteForm form, BindingResult bindingResult, Model model) 
    {

            if ( bindingResult.hasErrors() ) 
            {
                // return to the user login screen
                // Ausgabe auf der Konsole
                System.err.println("Binding has Errors:" + form); 
                // Eigene Fehlermeldung anzeigen
                model.addAttribute("registererror", "Fehlermeldung"); 
                // Rückkehr zum Registerscreen mit Fehlerinfo
                return getDelete(form, model);
            }   
        // check the form input
        System.out.println(form);    
        // redirect to login.html
        
        int p = rep.deleteOne(form.getUsername());
        System.out.println("---" + p);
        if(p == 0)
        {
            System.err.println("Password has Errors:" + form);
            model.addAttribute("deleteerror", "Fehlermeldung");
            return getDelete(form, model);

        }
        // template
        return "redirect:/home";

    }
}

