package com.edu.ctu.thesis.seafood.TraiNuoi;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.edu.ctu.thesis.seafood.ServiceHolder;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/api/seafood/trai-nuoi")
@CrossOrigin
@Log4j2
public class TraiNuoiResource {

    @Autowired
    TraiNuoiService traiNuoiService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping
    @Operation(summary = "Create Trai Nuoi")
    @ApiResponse(responseCode = "200", description = "Return Trai Nuoi", content = @Content(schema = @Schema(implementation = TraiNuoi.class)))
    public ResponseEntity<?> createTraiNuoi(@Valid @RequestBody TraiNuoi traiNuoi) {
        try {
            traiNuoi.setId(null);
            log.info("Creating new trai nuoi [{}] ...", traiNuoi.toString());
            TraiNuoi createdTraiNuoi = this.serviceHolder.createTraiNuoi(traiNuoi);
            log.info("Created new trai nuoi [{}] successfully!", createdTraiNuoi.getId());
            return ResponseEntity.ok(createdTraiNuoi);
        } catch (Exception e) {
            log.error("Cannot create new trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> removeTraiNuoi(@NotNull @PathVariable(value = "id") Long id) {
        try {
            log.info("Removing trai nuoi [{}] ...", id);
            this.traiNuoiService.remove(id);
            log.info("Removed trai nuoi [{}] successfully!", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Trai Nuoi [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTraiNuoi(@NotNull @PathVariable(value = "id") Long traiNuoiId,
            @Valid @RequestBody TraiNuoi traiNuoi) {
        try {
            log.info("Updating trai nuoi [{}] ...", traiNuoi.toString());
            traiNuoi.setId(traiNuoiId);
            TraiNuoi updatedTraiNuoi = this.traiNuoiService.updateTraiNuoi(traiNuoi);
            log.info("Updated trai nuoi [{}] successfully!", updatedTraiNuoi.getId());
            return ResponseEntity.ok(updatedTraiNuoi);
        } catch (Exception e) {
            log.error("Cannot update trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @PostMapping(path = "login")
    public ResponseEntity<?> getTraiNuoi(@Valid @RequestBody User user) {
        try {
            log.info("Getting trai nuoi [{}] ...", user.toString());
            TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
            log.info("Got trai nuoi [{}] successfully!", traiNuoiInDB.getId());
            return ResponseEntity.ok(traiNuoiInDB);
        } catch (Exception e) {
            log.error("Cannot get trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTraiNuois() {
        try {
            log.info("Getting all trai nuoi ...");
            List<TraiNuoi> traiNuois = this.traiNuoiService.getAllTraiNuoi();
            log.info("Got all trai nuoi [{}] successfully!", traiNuois.size());
            return ResponseEntity.ok(traiNuois);
        } catch (Exception e) {
            log.error("Cannot get all trai nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "vung-nuoi")
    @Operation(summary = "Create Vung Nuoi")
    @ApiResponse(responseCode = "200", description = "Return Vung Nuoi", content = @Content(schema = @Schema(implementation = VungNuoi.class)))
    public ResponseEntity<?> createVungNuoi(@Valid @RequestBody VungNuoi vungNuoi) {
        try {
            log.info("Creating new Vung Nuoi [{}] ...", vungNuoi.toString());
            VungNuoi vungNuoiCreated = this.serviceHolder.createVungNuoi(vungNuoi);
            log.info("Created new Vung Nuoi [{}] successfully!", vungNuoiCreated.getId());
            return ResponseEntity.ok(vungNuoiCreated);
        } catch (Exception e) {
            log.error("Cannot create new Vung Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
