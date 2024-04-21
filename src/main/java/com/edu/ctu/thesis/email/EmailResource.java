package com.edu.ctu.thesis.email;

import jakarta.validation.Valid;

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
@Log4j2
@CrossOrigin
@RequestMapping(path = "api/emails")
public class EmailResource {
    

    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailEntity email) {
        try {
            this.emailSenderService.sendSimpleEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body("Sent email successfully!");
        } catch (Exception e) {
            log.error("Cannot send the email: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
