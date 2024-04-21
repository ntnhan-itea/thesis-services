package com.edu.ctu.thesis.seafood.thagiong;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = "api/seafood/tha-giong")
public class ThaGiongResource {

    @Autowired
    ThaGiongService thaGiongService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting Tha Giong [{}] ...", id);
            ThaGiong thaGiongInDB = this.thaGiongService.findById(id, user);
            log.info("Got Tha Giong [{}] successfully!", thaGiongInDB.getId());
            return ResponseEntity.ok(thaGiongInDB);
        } catch (Exception e) {
            log.error("Cannot get Tha Giong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThaGiong thaGiong) {
        try {
            log.info("Updating Tha Giong [{}] ...", thaGiong.toString());
            thaGiong.setId(id);
            ThaGiong thaGiongUpdated = this.thaGiongService.update(thaGiong);
            log.info("Updated Tha Giong [{}]", thaGiongUpdated.getId());
            return ResponseEntity.ok(thaGiongUpdated);
        } catch (Exception e) {
            log.error("Cannot update Tha Giong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Tha Giong [{}] with user [{}] ...", id, user);
            this.thaGiongService.remove(id, user);
            log.info("Removed Tha Giong [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Tha Giong [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Tha Giong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
