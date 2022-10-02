package com.edu.ctu.thesis.seafood.TraiNuoi;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/api/seafood/trai-nuoi")
@CrossOrigin
@Log4j2
public class TraiNuoiResource {

    @Autowired
    TraiNuoiService traiNuoiService;

    @PostMapping
    public ResponseEntity<?> creatTraiNuoi(@Valid @RequestBody TraiNuoi traiNuoi) {
        try {
            log.info("Creating new trai nuoi [{}] ...", traiNuoi.toString());
            TraiNuoi createdTraiNuoi = this.traiNuoiService.createTraiNuoi(traiNuoi);
            log.info("Created new trai nuoi [{}] successfully!", createdTraiNuoi.getId());
            return ResponseEntity.ok(createdTraiNuoi);
        } catch (Exception e) {
            log.error("Cannot create new trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path = "login")
    public ResponseEntity<?> getTraiNuoi(@NotBlank @RequestParam("username") String username,
            @NotBlank @RequestParam("password") String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            log.info("Getting trai nuoi [{}] ...", user.toString());
            TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
            log.info("Got trai nuoi [{}] successfully!", traiNuoiInDB.getId());
            return ResponseEntity.ok(traiNuoiInDB);
        } catch (Exception e) {
            log.error("Cannot get trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTraiNuoi(@NotNull @PathVariable(value = "id") Long traiNuoiId, @Valid @RequestBody TraiNuoi traiNuoi) {
        try {
            log.info("Updating trai nuoi [{}] ...", traiNuoi.toString());
            traiNuoi.setId(traiNuoiId);
            TraiNuoi updatedTraiNuoi = this.traiNuoiService.updateTraiNuoi(traiNuoi);
            log.info("Updated trai nuoi [{}] successfully!", updatedTraiNuoi.getId());
            return ResponseEntity.ok(updatedTraiNuoi);
        } catch (Exception e) {
            log.error("Cannot update trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @PostMapping(path = "login")
    public ResponseEntity<?> getTraiNuoi(@Valid @RequestBody User user) {
        try {

            log.info("Getting trai nuoi [{}] ...", user.toString());
            TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
            log.info("Got trai nuoi [{}] successfully!", traiNuoiInDB.getId());
            return ResponseEntity.ok(traiNuoiInDB);
        } catch (Exception e) {
            log.error("Cannot get trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

   

}
