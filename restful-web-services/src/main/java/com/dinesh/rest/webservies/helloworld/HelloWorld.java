package com.dinesh.rest.webservies.helloworld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    private String message;

    public HelloWorld() {
        message = "Hello World!";
    }

    public HelloWorld(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HellWorld{" +
                "message='" + message + '\'' +
                '}';
    }
}
