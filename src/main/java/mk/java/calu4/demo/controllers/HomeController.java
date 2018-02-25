package mk.java.calu4.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping(value = "/hello")
  public String sayHello() {
    return "Hey you!";
  }
}
