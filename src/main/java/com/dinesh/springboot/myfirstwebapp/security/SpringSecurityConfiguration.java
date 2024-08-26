package com.dinesh.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.security.auth.kerberos.EncryptionKey;
import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = generateUser(passwordEncoder, "admin", "dinesh");
        UserDetails userDetails2 = generateUser(passwordEncoder, "dinesh", "raj");

        return new InMemoryUserDetailsManager(userDetails, userDetails2);
    }

    private UserDetails generateUser(Function<String, String> passwordEncoder, String password, String userName) {
        return User.builder()
                .passwordEncoder(passwordEncoder)
                .password(password)
                .username(userName)
                .roles("admin","user")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();}
}