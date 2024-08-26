package com.dinesh.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("user")
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage(Model themodel) {
        themodel.addAttribute("user",getLoggedInUsername());
        return "welcome-page";
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
