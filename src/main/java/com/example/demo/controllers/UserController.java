package com.example.demo.controllers;

import com.example.demo.model.jpa.UserJpa;
import com.example.demo.model.mongo.MongoUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/mongo")
    public ResponseEntity<MongoUser> createMongoUser(@RequestBody MongoUser mongoUser) {
        MongoUser createdMongoUser = userService.createMongoUser(mongoUser);
        return ResponseEntity.ok(createdMongoUser);
    }

    @PostMapping("/jpa")
    public ResponseEntity<UserJpa> createUserJpa(@RequestBody UserJpa userJpa) {
        UserJpa createdUserJpa = userService.createUserJpa(userJpa);
        return ResponseEntity.ok(createdUserJpa);
    }

    @GetMapping("/mongo/{id}")
    public ResponseEntity<MongoUser> getMongoUserById(@PathVariable String id) {
        MongoUser mongoUser = userService.getMongoUserById(id);
        if (mongoUser != null) {
            return ResponseEntity.ok(mongoUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<UserJpa> getUserJpaById(@PathVariable Long id) {
        UserJpa userJpa = userService.getUserJpaById(id);
        if (userJpa != null) {
            return ResponseEntity.ok(userJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mongo")
    public ResponseEntity<List<MongoUser>> getAllMongoUsers() {
        List<MongoUser> mongoUsers = userService.getAllMongoUsers();
        return ResponseEntity.ok(mongoUsers);
    }

    @GetMapping("/jpa")
    public ResponseEntity<List<UserJpa>> getAllJpaUsers() {
        List<UserJpa> userJpaList = userService.getAllJpaUsers();
        return ResponseEntity.ok(userJpaList);
    }

    @PutMapping("/mongo/{id}")
    public ResponseEntity<MongoUser> updateMongoUser(@PathVariable String id, @RequestBody MongoUser mongoUser) {
        MongoUser updatedMongoUser = userService.updateMongoUser(id, mongoUser);
        if (updatedMongoUser != null) {
            return ResponseEntity.ok(updatedMongoUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/jpa/{id}")
    public ResponseEntity<UserJpa> updateUserJpa(@PathVariable Long id, @RequestBody UserJpa userJpa) {
        UserJpa updatedUserJpa = userService.updateUserJpa(id, userJpa);
        if (updatedUserJpa != null) {
            return ResponseEntity.ok(updatedUserJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mongo/{id}")
    public ResponseEntity<Void> deleteMongoUser(@PathVariable String id) {
        userService.deleteMongoUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteUserJpa(@PathVariable Long id) {
        userService.deleteUserJpa(id);
        return ResponseEntity.noContent().build();
    }
}
