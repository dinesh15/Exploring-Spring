package com.dinesh.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class sayHelloController {


    @GetMapping("/hello")
    public String sayHello() {
        return "sayHello";
    }
}
