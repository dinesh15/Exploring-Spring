package com.dinesh.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.security.auth.kerberos.EncryptionKey;
import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authenticate all the urls
        http.authorizeRequests(auth -> auth.anyRequest().authenticated());
        // if not show login page
        http.formLogin(withDefaults());
        // disable csrf
        http.csrf().disable();
        // enable frames
        http.headers().frameOptions().disable();

        return http.build();
    }
}