package com.dinesh.rest.webservies.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordRESTController {

    MessageSource messageSource;

    private HelloWorld helloWorld;

    public HelloWordRESTController(HelloWorld helloWorld, MessageSource messageSource) {
        this.helloWorld = helloWorld;
        this.messageSource = messageSource;
    }

    @GetMapping("/helloworld")
    public HelloWorld getHelloWorld() {
        return helloWorld;
    }



    @GetMapping("/helloworld/{message}")
    public HelloWorld getHelloWorld(@PathVariable String message) {
        return new HelloWorld(message);
    }
    @GetMapping("/helloworld-i18n")
    public String getHelloWorldI18N() {
        Locale Locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message", Locale);
    }

}
