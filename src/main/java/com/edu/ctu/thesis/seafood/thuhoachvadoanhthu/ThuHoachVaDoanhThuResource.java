package com.edu.ctu.thesis.seafood.thuhoachvadoanhthu;

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
@Log4j2
@RequestMapping(path = "api/seafood/thu-hoach-va-doanh-thu")
public class ThuHoachVaDoanhThuResource {

    @Autowired
    ThuHoachVaDoanhThuService thuHoachVaDoanhThuService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Thu Hoach Va Doanh Thu [{}] ...");
            ThuHoachVaDoanhThu entityInDB = this.thuHoachVaDoanhThuService.findById(id, user);
            log.info("Got Thu Hoach Va Doanh Thu [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Thu Hoach Va Doanh Thu: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThuHoachVaDoanhThu ThuHoachVaDoanhThu) {
        try {
            ThuHoachVaDoanhThu.setId(id);
            log.info("Updating Thu Hoach Va Doanh Thu [{}] ...", ThuHoachVaDoanhThu.toString());
            ThuHoachVaDoanhThu entityUpdated = this.thuHoachVaDoanhThuService.update(ThuHoachVaDoanhThu);
            log.info("Updated Thu Hoach Va Doanh Thu [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Thu Hoach Va Doanh Thu: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Thu Hoach Va Doanh Thu [{}] with user [{}] ...", id, user);
            this.thuHoachVaDoanhThuService.remove(id, user);
            log.info("Removed Thu Hoach Va Doanh Thu [{}]", id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Removed Thu Hoach Va Doanh Thu [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Thu Hoach Va Doanh Thu: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
