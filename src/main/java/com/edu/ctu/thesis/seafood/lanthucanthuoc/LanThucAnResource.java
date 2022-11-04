package com.edu.ctu.thesis.seafood.lanthucanthuoc;

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
@RequestMapping(path = "api/seafood/lan-thuc-an-thuoc")
public class LanThucAnResource {
    
    @Autowired
    LanThucAnThuocService lanThucAnThuocService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Lan Thuc An Thuoc [{}] ...");
            LanThucAnThuoc entityInDB = this.lanThucAnThuocService.findById(id, user);
            log.info("Got Lan Thuc An Thuoc [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Lan Thuc An Thuoc: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody LanThucAnThuoc lanThucAnThuoc) {
        try {
            lanThucAnThuoc.setId(id);
            log.info("Updating Lan Thuc An Thuoc [{}] ...", lanThucAnThuoc.toString());
            LanThucAnThuoc entityUpdated = this.lanThucAnThuocService.update(lanThucAnThuoc);
            log.info("Updated Lan Thuc An Thuoc [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Lan Thuc An Thuoc: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Lan Thuc An Thuoc [{}] with user [{}] ...", id, user);
            this.lanThucAnThuocService.remove(id, user);
            log.info("Removed Lan Thuc An Thuoc [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Lan Thuc An Thuoc [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Lan Thuc An Thuoc: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
