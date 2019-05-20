package com.starshot.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  /**
   * Login Page.
   * @param model - model for jsp
   * @param error - error message
   * @return login
   */
  @GetMapping("/login")
  public String getLoginPage(Model model, String error) {

    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    return "login";
  }

  /**
   * Logout.
   * @return back to login page
   */
  @PostMapping("/logout")
  public String logout() {
    return "login";
  }

}
