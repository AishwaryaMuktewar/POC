package com.example.demo;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.dao.UserDAOService;
import com.example.demo.pojos.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService dao;

    @GetMapping("/users/{id}")
    public EntityModel<User> oneUser(@PathVariable int id) {
        User user = dao.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User Not Found Exception");
        }
        Link selfLink = linkTo(methodOn(UserResource.class).oneUser(id)).withSelfRel();
        user.add(selfLink);
        Link link = linkTo(methodOn(UserResource.class).allUsers()).withRel("allUsers");
        EntityModel<User> result = new EntityModel<User>(user,link);
        return result;
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return dao.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = dao.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = dao.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("User Not Found Exception");
        }
        return user;
    }
}
