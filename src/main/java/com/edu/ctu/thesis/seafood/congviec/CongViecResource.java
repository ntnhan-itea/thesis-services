package com.edu.ctu.thesis.seafood.congviec;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
@RequestMapping(path = "api/seafood/cong-viec")
public class CongViecResource {

    @Autowired
    CongViecService congViecService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Cong Viec [{}] ...");
            CongViec entityInDB = this.congViecService.findById(id, user);
            log.info("Got Cong Viec [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Cong Viec: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody CongViec CongViec) {
        try {
            CongViec.setId(id);
            log.info("Updating Cong Viec [{}] ...", CongViec.toString());
            CongViec entityUpdated = this.congViecService.update(CongViec);
            log.info("Updated Cong Viec [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Cong Viec: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Cong Viec [{}] with user [{}] ...", id, user);
            this.congViecService.remove(id, user);
            log.info("Removed Cong Viec [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Cong Viec [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Cong Viec: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
