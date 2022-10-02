package com.edu.ctu.thesis.seafood.vungnuoi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/seafood/vung-nuoi")
@CrossOrigin
@Log4j2
public class VungNuoiResource {

    @Autowired
    VungNuoiService vungNuoiService;

    @PostMapping
    public ResponseEntity<?> createVungNuoi(@Valid @RequestBody VungNuoi vungNuoi) {
        try {
            log.info("Creating new vung nuoi [{}] ...", vungNuoi.toString());
            VungNuoi vungNuoiCreated = this.vungNuoiService.createVungNuoi(vungNuoi);
            log.info("Created vung nuoi [{}] successfully!", vungNuoiCreated.getTenVungNuoi());
            return ResponseEntity.ok(vungNuoiCreated);
        } catch (Exception e) {
            log.error("Cannot create new vung nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateVungNuoi(@NotNull @PathVariable(value = "id") Long vungNuoiId,
            @Valid @RequestBody VungNuoi vungNuoi) {
        try {
            vungNuoi.setId(vungNuoiId);
            log.info("Updating vung nuoi [{}] ...", vungNuoi.toString());
            VungNuoi vungNuoiUpdated = this.vungNuoiService.updateVungNuoi(vungNuoi);
            log.info("Updated vung nuoi [{}] successfully!", vungNuoiUpdated.getId());
            return ResponseEntity.ok(vungNuoiUpdated);
        } catch (Exception e) {
            log.error("Cannot update vung nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
