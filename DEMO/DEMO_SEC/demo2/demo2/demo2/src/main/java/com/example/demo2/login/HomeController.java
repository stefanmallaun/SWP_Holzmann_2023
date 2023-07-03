package com.example.demo2.login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

   
  // Method for GET request of home screen
  @GetMapping("/home")
  public String getHome(Model model, HttpServletRequest request) {
    // Fragment auswaehlen
    HttpSession session = request.getSession();
      Person b = (Person)session.getAttribute("person");
      model.addAttribute("person",b);
    model.addAttribute("contents", "home::home_contents");
    return "homeLayout";
  }

  // Logout process
  @PostMapping("/logout")
  public String postLogout() {
    // Redirect to login screen
    return "redirect:/login";
   }

  

  @GetMapping("/admin")
  public String getUserList(Model model) {
    // Fragment waehlen
    model.addAttribute("contents", "admin::admin_contents");
    // User Daten holen
    return "admin";
   }

}