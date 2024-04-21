package com.edu.ctu.thesis.seafood.ketquathuhoach;

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

import com.edu.ctu.thesis.seafood.ServiceHolder;
import com.edu.ctu.thesis.seafood.thuhoachvadoanhthu.ThuHoachVaDoanhThu;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = "api/seafood/ket-qua-thu-hoach")
public class KetQuaThuHoachResource {
    
    @Autowired
    KetQuaThuHoachService ketQuaThuHoachService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Ket Qua Thu Hoach [{}] ...");
            KetQuaThuHoach entityInDB = this.ketQuaThuHoachService.findById(id, user);
            log.info("Got Ket Qua Thu Hoach [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Ket Qua Thu Hoach: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody KetQuaThuHoach ketQuaThuHoach) {
        try {
            ketQuaThuHoach.setId(id);
            log.info("Updating Ket Qua Thu Hoach [{}] ...", ketQuaThuHoach.toString());
            KetQuaThuHoach entityUpdated = this.ketQuaThuHoachService.update(ketQuaThuHoach);
            log.info("Updated Ket Qua Thu Hoach [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Ket Qua Thu Hoach: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Ket Qua Thu Hoach [{}] with user [{}] ...", id, user);
            this.ketQuaThuHoachService.remove(id, user);
            log.info("Removed Ket Qua Thu Hoach [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Ket Qua Thu Hoach [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Ket Qua Thu Hoach: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    
    @PostMapping(path = "{id}/thu-hoach-va-doanh-thu")
    public ResponseEntity<?> createLanThucAnThuoc(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThuHoachVaDoanhThu thuHoachVaDoanhThu) {
        try {
            log.info("Creating Thu Hoach Va Doanh Thu [{}] of Ket Qua Thu Hoach [{}] ...", thuHoachVaDoanhThu.toString(), id);
            ThuHoachVaDoanhThu entityCreated = this.serviceHolder.createThuHoachVaDoanhThu(id, thuHoachVaDoanhThu);
            log.info("Created Thu Hoach Va Doanh Thu [{}] of Ket Qua Thu Hoach [{}] successfully!", entityCreated.getId(), id);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
        } catch (Exception e) {
            log.error("Cannot create Thu Hoach Va Doanh Thu: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
