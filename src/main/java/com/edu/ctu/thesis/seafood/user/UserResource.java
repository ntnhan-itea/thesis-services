package com.edu.ctu.thesis.seafood.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/api/seafood/user")
@CrossOrigin
@Log4j2
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping(path = "create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            log.info("Creating new user [{}] ...", user.toString());
            User createdUser = this.userService.createUser(user);
            log.info("Created new user successfully: [{}] ...", createdUser.getId());
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            log.error("Cannot create new user: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> getUser(@RequestBody User user) {
        try {
            log.info("Getting user [{}] ...", user.toString());
            User userInDB = this.userService.getUser(user);
            log.info("Got user successfully: [{}] ...", userInDB.getUsername());
            return ResponseEntity.ok(userInDB);
        } catch (Exception e) {
            log.error("Cannot create new user: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
