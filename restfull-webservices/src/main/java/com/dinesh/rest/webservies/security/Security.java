package com.dinesh.rest.webservies.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // enable authentication
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        // instead of the default page show the pop up if a request is not authenticated update
        http.httpBasic(withDefaults());
        // disable csrf
        http.csrf().disable();

        return http.build();
    }
}
