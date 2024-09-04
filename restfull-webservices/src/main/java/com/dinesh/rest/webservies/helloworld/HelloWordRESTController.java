package com.dinesh.rest.webservies.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordRESTController {

    private HelloWorld helloWorld;

    public HelloWordRESTController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @GetMapping("/helloworld")
    public HelloWorld getHelloWorld() {
        return helloWorld;
    }

    @GetMapping("/helloworld/{message}")
    public HelloWorld getHelloWorld(@PathVariable String message) {
        return new HelloWorld(message);
    }

}
