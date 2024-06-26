package com.edu.ctu.thesis.seafood.admin;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = "api/seafood/admin")
public class AdminUserResource {

    @Autowired
    AdminService adminService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AdminUser adminUser) {
        // try {
        log.info("Creating new admin user [{}] ...", adminUser.toString());
        AdminUser entityCreated = this.adminService.create(adminUser);
        log.info("Created Admin user [{}] successfully!", entityCreated.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
        // } catch (Exception e) {
        // log.error("Cannot create new admin user: ", e);
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        // }
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> getUser(@Valid @RequestBody User user) {
        // try {
        log.info("Getting AdminUser [{}] ...", user.toString());
        AdminUser userInDB = this.adminService.getUser(user);
        log.info("Got AdminUser [{}] successfully!", userInDB.getUsername());
        return ResponseEntity.ok(userInDB);
        // } catch (Exception e) {
        // log.error("Cannot get user: ", e);
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        // }
    }

}
