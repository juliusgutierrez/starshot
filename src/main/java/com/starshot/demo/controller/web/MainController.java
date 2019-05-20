package com.starshot.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by juliusgutierrez on 18/05/2019.
 */
@Controller
public class MainController {

  @GetMapping("/")
  public String getMainPage(Model model) {
    return "main";
  }

}
