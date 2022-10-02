package com.edu.ctu.thesis.seafood.point;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping(value = "api/seafood/point")
@Log4j2
public class PointResource {

    @Autowired
    PointService pointService;

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deletePoint(@NotNull @PathVariable(value = "id") Long id) {
        try {
            log.info("Deleting point [{}] ...", id);
            this.pointService.deleteById(id);
            log.info("Deleted point [{}] successfully!", id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("Cannot delete point [{}]", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
