package com.dinesh.rest.webservies.SocialMedia;

import com.dinesh.rest.webservies.user.User;
import com.dinesh.rest.webservies.user.UserDaoService;
import com.dinesh.rest.webservies.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        User user = userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User addedUser = userDaoService.add(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
