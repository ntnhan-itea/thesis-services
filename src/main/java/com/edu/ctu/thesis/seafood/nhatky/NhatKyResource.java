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
import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoach;
import com.edu.ctu.thesis.seafood.lantheodoitangtruong.LanTheoDoiTangTruong;
import com.edu.ctu.thesis.seafood.thagiong.ThaGiong;
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
            return ResponseEntity.status(HttpStatus.OK).body("Removed Nhat Ky [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Nhat Ky: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/chuan-bi-ao-nuoi")
    public ResponseEntity<?> createChuanBiAoNuoi(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ChuanBiAoNuoi chuanBiAoNuoi) {
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

    @PostMapping(path = "{id}/tha-giong")
    public ResponseEntity<?> createThaGiong(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThaGiong thaGiong) {
        try {
            log.info("Creating Tha Giong [{}] of Nhat Ky [{}] ...", thaGiong.toString(), id);
            ThaGiong thaGiongCreated = this.serviceHolder.createThaGiong(id, thaGiong);
            log.info("Created Tha Giong [{}] of Nhat Ky [{}] ...", thaGiongCreated.getId(), id);
            return ResponseEntity.ok(thaGiongCreated);
        } catch (Exception e) {
            log.error("Cannot create Tha Giong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/ket-qua-thu-hoach")
    public ResponseEntity<?> createKetQuaThuHoach(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody KetQuaThuHoach ketQuaThuHoach) {
        try {
            log.info("Creating Ket Qua Thu Hoach [{}] of Nhat Ky [{}] ...", ketQuaThuHoach.toString(), id);
            KetQuaThuHoach ketQuaThuHoachCreated = this.serviceHolder.createKetQuaThuHoach(id, ketQuaThuHoach);
            log.info("Created Ket Qua Thu Hoach [{}] of Nhat Ky [{}] ...", ketQuaThuHoachCreated.getId(), id);
            return ResponseEntity.status(HttpStatus.CREATED).body(ketQuaThuHoachCreated);
        } catch (Exception e) {
            log.error("Cannot create Ket Qua Thu Hoach: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/lan-theo-doi-tang-truong")
    public ResponseEntity<?> createLanTheoDoiTangTruong(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        try {
            log.info("Creating Lan Theo Doi Tang Truong [{}] of Nhat Ky [{}] ...", lanTheoDoiTangTruong.toString(), id);
            LanTheoDoiTangTruong lanTheoDoiTangTruongCreated = this.serviceHolder.createLanTheoDoiTangTruong(id, lanTheoDoiTangTruong);
            log.info("Created Lan Theo Doi Tang Truong [{}] of Nhat Ky [{}] ...", lanTheoDoiTangTruongCreated.getId(), id);
            return ResponseEntity.status(HttpStatus.CREATED).body(lanTheoDoiTangTruongCreated);
        } catch (Exception e) {
            log.error("Cannot create Lan Theo Doi Tang Truong: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
