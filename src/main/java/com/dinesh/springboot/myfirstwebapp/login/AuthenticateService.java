package com.dinesh.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

    public boolean authenticate(String name,String password){

        boolean isValidUserName = name.equalsIgnoreCase("dinesh");
        boolean isValidPassword = password.equalsIgnoreCase("raj");

        return isValidUserName && isValidPassword;
    }
}
