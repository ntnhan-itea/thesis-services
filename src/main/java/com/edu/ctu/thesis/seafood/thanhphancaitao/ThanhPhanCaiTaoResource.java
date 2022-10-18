package com.edu.ctu.thesis.seafood.thanhphancaitao;

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
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping(path = "api/seafood/thanh-phan-cai-tao")
public class ThanhPhanCaiTaoResource {

    @Autowired
    ThanhPhanCaiTaoService thanhPhanCaiTaoService;

    @Autowired
    ServiceHolder serviceHolder;

    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Getting Thanh Phan Cai Tao [{}] ...", id);
            ThanhPhanCaiTao thanhPhanCaiTaoInDB = this.thanhPhanCaiTaoService.findById(id, user);
            log.info("Got Thanh Phan Cai Tao [{}] successfully!", thanhPhanCaiTaoInDB.getId());
            return ResponseEntity.ok(thanhPhanCaiTaoInDB);
        } catch (Exception e) {
            log.error("Cannot get Thanh Phan Cai Tao: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody ThanhPhanCaiTao thanhPhanCaiTao) {
        try {
            thanhPhanCaiTao.setId(id);
            log.info("Updating Thanh Phan Cai Tao [{}] ...", thanhPhanCaiTao.toString());
            ThanhPhanCaiTao thanhPhanCaiTaoUpdated = this.thanhPhanCaiTaoService.update(thanhPhanCaiTao);
            log.info("Updated Thanh Phan Cai Tao [{}]", thanhPhanCaiTaoUpdated.getId());
            return ResponseEntity.ok(thanhPhanCaiTaoUpdated);
        } catch (Exception e) {
            log.error("Cannot update Thanh Phan Cai Tao: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Thanh Phan Cai Tao [{}] with user [{}] ...", id, user);
            this.thanhPhanCaiTaoService.remove(id, user);
            log.info("Removed Thanh Phan Cai Tao [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("Cannot remove Thanh Phan Cai Tao: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
