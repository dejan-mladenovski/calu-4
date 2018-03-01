package mk.java.calu4.demo.controllers;

import mk.java.calu4.demo.models.Shelter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ShelterController {

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newShelter(ModelMap map, HttpSession httpSession) {
    // check is user is logged in
    if (httpSession.getAttribute("loggedInUser") != null) {
      map.addAttribute("shelter", new Shelter());
      return "new";
    }
    return "forbidden";
  }
}