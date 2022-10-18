package com.edu.ctu.thesis.seafood.nhatky;

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
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoi;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping(value = "api/seafood/nhat-ky")
@Log4j2
public class NhatKyResource {

    @Autowired
    NhatKyService nhatKyService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting Nhat Ky [{}] ...", id);
            NhatKy NhatKyInDB = this.nhatKyService.findById(id, user);
            log.info("Got Nhat Ky [{}] successfully!", NhatKyInDB.getId());
            return ResponseEntity.ok(NhatKyInDB);
        } catch (Exception e) {
            log.error("Cannot get Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/chuan-bi-ao-nuoi")
    public ResponseEntity<?> createChuanBiAoNuoi(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody ChuanBiAoNuoi chuanBiAoNuoi ) {
        try {
            log.info("Creating Chuan Bi Ao Nuoi [{}] of Nhat Ky [{}] ...", chuanBiAoNuoi.toString(), id);
            ChuanBiAoNuoi chuanBiAoNuoiCreated = this.serviceHolder.createChuanBiAoNuoi(id, chuanBiAoNuoi);
            log.info("Created Chuan Bi Ao Nuoi [{}] of Nhat Ky [{}] ...", chuanBiAoNuoiCreated.getId(), id);
            return ResponseEntity.ok(chuanBiAoNuoiCreated);
        } catch (Exception e) {
            log.error("Cannot create Chuan Bi Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "{aoNuoiId}")
    public ResponseEntity<?> create(
            @NotNull @PathVariable(value = "aoNuoiId") Long id,
            @Valid @RequestBody NhatKy nhatKy) {
        try {
            log.info("Creating Nhat Ky [{}] of Ao Nuoi [{}] ...", nhatKy, id);
            NhatKy nhatKyCreated = this.serviceHolder.createNhatKy(id, nhatKy);
            log.info("Created Nhat Ky [{}]", nhatKyCreated.getId());
            return ResponseEntity.ok(nhatKyCreated);
        } catch (Exception e) {
            log.error("Cannot create Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody NhatKy nhatKy) {
        try {
            log.info("Updating Nhat Ky [{}] ...", nhatKy.toString());
            NhatKy nhatKyUpdated = this.nhatKyService.update(id, nhatKy);
            log.info("Updated Nhat Ky [{}]", nhatKyUpdated.getId());
            return ResponseEntity.ok(nhatKyUpdated);
        } catch (Exception e) {
            log.error("Cannot update Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Nhat Ky [{}] with user [{}] ...", id, user);
            this.nhatKyService.remove(id, user);
            log.info("Removed Nhat Ky [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("Cannot remove Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
