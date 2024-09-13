package com.dinesh.rest.webservies.SocialMedia;

import com.dinesh.rest.webservies.user.User;
import com.dinesh.rest.webservies.user.UserDaoService;
import com.dinesh.rest.webservies.user.UserNotFoundException;
import com.dinesh.rest.webservies.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RestSocialMediaController {

    UserDaoService userDaoService;
    UserRepository userRepository;

    public RestSocialMediaController(UserDaoService userDaoService, UserRepository userRepository ) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id) {

        Optional<User> user =userRepository.findById(id);
        System.out.println(user);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(link.withRel("all-users"));

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id); //
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User addedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
