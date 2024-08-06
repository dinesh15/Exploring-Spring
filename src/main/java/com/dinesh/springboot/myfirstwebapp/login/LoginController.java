package com.dinesh.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("user")
public class LoginController {

    AuthenticateService authenticateService;

    public LoginController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @GetMapping("/login")
    public String loginPage(Model themodel, @ModelAttribute("user") User user) {
        themodel.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/login")
    public String gotowelcome(@ModelAttribute User user, Model model) {
        if (authenticateService.authenticate(user.getUserName(), user.getPassword())) {
            model.addAttribute("name", user.getUserName());
            return "welcome-page";
        } else {
            model.addAttribute("message", "invalid credentials");
            return "login";
        }
    }
}
