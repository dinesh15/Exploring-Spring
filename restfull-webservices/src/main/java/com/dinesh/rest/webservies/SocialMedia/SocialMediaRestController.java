package com.dinesh.rest.webservies.SocialMedia;

import com.dinesh.rest.webservies.post.Post;
import com.dinesh.rest.webservies.post.PostRepository;
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
public class SocialMediaRestController {

    UserRepository userRepository;
    PostRepository postRepository;

    public SocialMediaRestController(PostRepository postRepository, UserRepository userRepository ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        User addedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
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
        userRepository.deleteById(id);
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> getPostOfUsers(@PathVariable int id) {
        Optional<User> user =userRepository.findById(id);
//        System.out.println(user);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user =userRepository.findById(id);

        System.out.println(post.getDescription());

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/jpa/users/{id}/posts/{post_id}")
    public EntityModel<Post> retrievePost(@PathVariable int id, @PathVariable int post_id) {
        Optional<Post> post = postRepository.findById(post_id);
        if (post.isEmpty())
            throw new UserNotFoundException("id:" + post_id);
        EntityModel<Post> entityModel = EntityModel.of(post.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getPostOfUsers(id));
        entityModel.add(link.withRel("all-posts"));
        return entityModel;
    }
}
