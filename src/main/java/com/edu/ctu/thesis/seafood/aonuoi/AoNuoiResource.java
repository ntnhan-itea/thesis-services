package com.edu.ctu.thesis.seafood.aonuoi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/seafood/ao-nuoi")
@CrossOrigin
@Log4j2
public class AoNuoiResource {

    @Autowired
    AoNuoiService aoNuoiService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping(path = "{id}/nhat-ky")
    public ResponseEntity<?> createNhatKy(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody NhatKy nhatKy) {
        try {
            log.info("Creating Nhat Ky [{}] of Ao Nuoi [{}] ...", nhatKy.toString(), id);
            NhatKy nhatKyCreated = this.serviceHolder.createNhatKy(id, nhatKy);
            log.info("Created Nhat Ky [{}]", nhatKyCreated.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(nhatKyCreated);
        } catch (Exception e) {
            log.error("Cannot create Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/nhat-ky/get-latest-added")
    public ResponseEntity<?> getLatestNhatKy(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting latest Nhat Ky of Ao Nuoi [{}] ...", id);
            NhatKy nhatKy = this.serviceHolder.getLastestNhatKyOfAoNuoi(id, user);
            log.info("Got latest Nhat Ky [{}]", nhatKy.getId());
            return ResponseEntity.ok(nhatKy);
        } catch (Exception e) {
            log.error("Cannot get latest Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAoNuoiById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        log.info("Getting Ao Nuoi by id [{}] with User [{}] ...", id, user.toString());
        AoNuoi aoNuoi = this.aoNuoiService.findByIdAndUser(id, user);
        log.info("Got Ao Nuoi [{}] successfully!", aoNuoi.getId());
        return ResponseEntity.ok(aoNuoi);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAoNuoi(@NotNull @PathVariable("id") Long id,
            @Valid @RequestBody AoNuoi aoNuoi) {
        try {
            aoNuoi.setId(id);
            log.info("Updating Ao Nuoi [{}] ...", aoNuoi.toString());
            AoNuoi updatedAoNuoi = this.aoNuoiService.updateAoNuoi(aoNuoi);
            log.info("updated Ao Nuoi [{}] successfully!", updatedAoNuoi.getId());
            return ResponseEntity.ok(updatedAoNuoi);
        } catch (Exception e) {
            log.error("Cannot update Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> removeAoNuoi(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Ao Nuoi [{}] with user [{}] ...", id, user);
            this.aoNuoiService.removeAoNuoi(id, user);
            log.info("Removed Ao Nuoi [{}] successfully!", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Ao Nuoi [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
