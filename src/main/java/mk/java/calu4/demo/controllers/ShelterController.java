package mk.java.calu4.demo.controllers;

import mk.java.calu4.demo.models.Shelter;
import mk.java.calu4.demo.repositories.SheltersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class ShelterController {

  @Autowired
  private SheltersRepository sheltersRepository;

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newShelter(ModelMap map, HttpSession httpSession) {
    // check is user is logged in
    if (httpSession.getAttribute("loggedInUser") != null) {
      map.addAttribute("shelter", new Shelter());
      return "new";
    }
    return "forbidden";
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String saveNewShelter(@ModelAttribute Shelter newShelter, HttpSession httpSession) {
    // check is user is logged in
    if (httpSession.getAttribute("loggedInUser") != null) {
      newShelter.setDateCreated(LocalDateTime.now());
      sheltersRepository.save(newShelter);
      return "redirect:index";
    }
    return "forbidden";
  }

  @RequestMapping(path="/edit", method = RequestMethod.GET)
  public String showEditPage(@RequestParam("id") Long id, ModelMap map, HttpSession httpSession) {
    if (httpSession.getAttribute("loggedInUser") != null) {

      Shelter shelter = sheltersRepository.findById(id).orElse(null);

      map.addAttribute("shelter", shelter);
      return "edit";
    }
    return "forbidden";
  }

  @RequestMapping(path="/edit", method = RequestMethod.POST)
  public String processEditPage(@ModelAttribute Shelter shelter, HttpSession httpSession) {
    if (httpSession.getAttribute("loggedInUser") != null) {

      sheltersRepository.save(shelter);

      return "redirect:index";
    }
    return "forbidden";
  }
}