package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTao;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(value = "api/seafood/chuan-bi-ao-nuoi")
public class ChuanBiAoResource {

    @Autowired
    ServiceHolder serviceHolder;

    @Autowired
    ChuanBiAoNuoiService chuanBiAoNuoiService;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting Chuan Bi Ao Nuoi [{}] ...", id);
            ChuanBiAoNuoi ChuanBiAoNuoiInDB = this.chuanBiAoNuoiService.findById(id, user);
            log.info("Got Chuan Bi Ao Nuoi [{}] successfully!", ChuanBiAoNuoiInDB.getId());
            return ResponseEntity.ok(ChuanBiAoNuoiInDB);
        } catch (Exception e) {
            log.error("Cannot get Chuan Bi Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateChuanBiAoNuoi(@NotNull @PathVariable("id") Long id,
            @Valid @RequestBody ChuanBiAoNuoi chuanBiAoNuoi) {
        try {
            chuanBiAoNuoi.setId(id);
            log.info("Updating Chuan Bi Ao Nuoi [{}] ...", chuanBiAoNuoi.toString());
            ChuanBiAoNuoi ChuanBiAoNuoiUpdated = this.chuanBiAoNuoiService.update(chuanBiAoNuoi);
            log.info("updated Chuan Bi Ao Nuoi [{}] successfully!", ChuanBiAoNuoiUpdated.getId());
            return ResponseEntity.ok(ChuanBiAoNuoiUpdated);
        } catch (Exception e) {
            log.error("Cannot update Chuan Bi Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> removeAoNuoi(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Chuan Bi Ao Nuoi [{}] with user [{}] ...", id, user);
            this.chuanBiAoNuoiService.removeById(id, user);
            log.info("Removed Chuan Bi Ao Nuoi [{}] successfully!", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Chuan Bi Ao Nuoi [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Chuan Bi Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(path = "{id}/thanh-phan-cai-tao")
    public ResponseEntity<?> createThanhPhanCaiTao(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThanhPhanCaiTao thanhPhanCaiTao) {
        try {
            log.info("Creating Thanh Phan Cai Tao [{}] of Chuan Bi Ao [{}] ...", thanhPhanCaiTao.toString(), id);
            ThanhPhanCaiTao thanhPhanCaiTaoCreated = this.serviceHolder.createThanhPhanCaiTao(id, thanhPhanCaiTao);
            log.info("Created Thanh Phan Cai Tao [{}]", thanhPhanCaiTaoCreated.getId());
            return ResponseEntity.ok(thanhPhanCaiTaoCreated);
        } catch (Exception e) {
            log.error("Cannot create Thanh Phan Cai Tao: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
