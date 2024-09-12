package com.dinesh.rest.webservies.SocialMedia;

import com.dinesh.rest.webservies.user.User;
import com.dinesh.rest.webservies.user.UserDaoService;
import com.dinesh.rest.webservies.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> getUser(@PathVariable int id) {

        User user = userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(link.withRel("all-users"));

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User addedUser = userDaoService.add(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
