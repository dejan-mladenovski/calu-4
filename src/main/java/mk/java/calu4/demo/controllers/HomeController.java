package mk.java.calu4.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping(value = "/hello")
  public String sayHello(Model model) {
    model.addAttribute("message", "Hey there! Do you like Spring?");
    return "hello";
  }
}
