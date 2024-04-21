package com.edu.ctu.thesis.seafood.lantheodoitangtruong;

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
@RequestMapping(path = "api/seafood/lan-theo-doi-tang-truong")
@Log4j2
public class LanTheoDoiTangTruongResource {
    
    @Autowired
    LanTheoDoiTangTruongService lanTheoDoiTangTruongService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Lan Theo Doi Tang Truong [{}] ...");
            LanTheoDoiTangTruong entityInDB = this.lanTheoDoiTangTruongService.findById(id, user);
            log.info("Got Lan Theo Doi Tang Truong [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Lan Theo Doi Tang Truong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        try {
            lanTheoDoiTangTruong.setId(id);
            log.info("Updating Lan Theo Doi Tang Truong [{}] ...", lanTheoDoiTangTruong.toString());
            LanTheoDoiTangTruong entityUpdated = this.lanTheoDoiTangTruongService.update(lanTheoDoiTangTruong);
            log.info("Updated Lan Theo Doi Tang Truong [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Lan Theo Doi Tang Truong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Lan Theo Doi Tang Truong [{}] with user [{}] ...", id, user);
            this.lanTheoDoiTangTruongService.remove(id, user);
            log.info("Removed Lan Theo Doi Tang Truong [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Lan Theo Doi Tang Truong [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Lan Theo Doi Tang Truong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    

}
