package com.dinesh.rest.webservies.SocialMedia;

import com.dinesh.rest.webservies.user.User;
import com.dinesh.rest.webservies.user.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestSocialMediaController {

    UserDaoService userDaoService;

    public RestSocialMediaController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userDaoService.getUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userDaoService.add(user);
        return ResponseEntity.created(null).build();
    }
}
