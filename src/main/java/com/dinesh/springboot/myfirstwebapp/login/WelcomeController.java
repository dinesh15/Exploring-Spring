package com.dinesh.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("user")
public class WelcomeController {

    @GetMapping("/")
    public String welcomePage(Model themodel) {
        themodel.addAttribute("user","Dinesh");
//        themodel.addAttribute("user",user);
        return "welcome-page";
    }

}
