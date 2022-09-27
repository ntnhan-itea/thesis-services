package com.edu.ctu.thesis.seafood.TraiNuoi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/api/seafood/trai-nuoi")
@CrossOrigin
@Log4j2
public class TraiNuoiResource {

    @Autowired
    TraiNuoiService traiNuoiService;

    public ResponseEntity<?> creatTraiNuoi(TraiNuoi traiNuoi) {
        try {
            log.info("Creating new trai nuoi [{}] ...", traiNuoi.toString());
            TraiNuoi createdTraiNuoi = this.traiNuoiService.createTraiNuoi(traiNuoi);
            log.info("Created new trai nuoi successfully: [{}] ...", createdTraiNuoi.getId());
            return ResponseEntity.ok(createdTraiNuoi);
        } catch (Exception e) {
            log.error("Cannot create new trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
