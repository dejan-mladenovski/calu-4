package mk.java.calu4.demo.controllers;

import mk.java.calu4.demo.models.User;
import mk.java.calu4.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class LoginController {

  @Autowired
  private UsersRepository userRepository;

  @RequestMapping(value = "login", method = RequestMethod.GET)
  public String login(ModelMap map) {
    map.addAttribute("user", new User());
    return "login";
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String doLogin(@ModelAttribute User user, HttpSession session /*, ModelMap map*/) {
    if (user != null && user.getUsername() != null) {
      // perform check
      User foundUser = userRepository.findByUsername(user.getUsername());
      if (foundUser!= null && foundUser.getPassword().equals(user.getPassword())) {
        session.setAttribute("loggedInUser", foundUser);
        return "success";
      }
    }
    // user failed to authenticate
    return "forbidden";
  }
}