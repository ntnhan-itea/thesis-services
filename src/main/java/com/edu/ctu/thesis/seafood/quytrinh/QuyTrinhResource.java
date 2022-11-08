package com.edu.ctu.thesis.seafood.quytrinh;

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

import com.edu.ctu.thesis.seafood.ServiceHolder;
import com.edu.ctu.thesis.seafood.congviec.CongViec;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = "api/seafood/quy-trinh")
public class QuyTrinhResource {

    @Autowired
    QuyTrinhService quyTrinhService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting Quy Trinh [{}] ...", id);
            QuyTrinh entityInDB = this.quyTrinhService.findById(id, user);
            log.info("Got Quy Trinh [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Quy Trinh: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody QuyTrinh quyTrinh) {
        try {
            log.info("Updating Quy Trinh [{}] ...", quyTrinh.toString());
            quyTrinh.setId(id);
            QuyTrinh entityCreated = this.quyTrinhService.update(quyTrinh);
            log.info("Updated Quy Trinh [{}]", entityCreated.getId());
            return ResponseEntity.ok(entityCreated);
        } catch (Exception e) {
            log.error("Cannot update Quy Trinh: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Quy Trinh [{}] with user [{}] ...", id, user);
            this.quyTrinhService.remove(id, user);
            log.info("Removed Quy Trinh [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Quy Trinh [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Quy Trinh: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    
    @PostMapping(path = "{id}/cong-viec")
    public ResponseEntity<?> createCongViec(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody CongViec congViec) {
        try {
            log.info("Creating Cong Viec [{}] of Quy Trinh [{}] ...", congViec.toString(), id);
            CongViec congViecCreated = this.serviceHolder.createCongViec(id, congViec);
            log.info("Created Cong Viec [{}] of Quy Trinh [{}] successfully!", congViecCreated.getId(), id);
            return ResponseEntity.status(HttpStatus.CREATED).body(congViecCreated);
        } catch (Exception e) {
            log.error("Cannot create Cong Viec: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
