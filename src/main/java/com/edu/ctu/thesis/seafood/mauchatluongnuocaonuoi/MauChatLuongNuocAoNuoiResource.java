package com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi;

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
@Log4j2
@CrossOrigin
@RequestMapping(path = "api/seafood/mau-chat-luong-nuoc-ao-nuoi")
public class MauChatLuongNuocAoNuoiResource {
    
    @Autowired
    MauChatLuongNuocAoNuoiService mauChatLuongNuocAoNuoiService;

    
    @PostMapping(path = "get-by-id/{id}")
    public ResponseEntity<?> getById(@NotNull @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        try {
            log.info("Getting Mau Chat Luong Nuoc Ao Nuoi [{}] ...");
            MauChatLuongNuocAoNuoi entityInDB = this.mauChatLuongNuocAoNuoiService.findById(id, user);
            log.info("Got Mau Chat Luong Nuoc Ao Nuoi [{}] successfully!", entityInDB.getId());
            return ResponseEntity.ok(entityInDB);
        } catch (Exception e) {
            log.error("Cannot get Mau Chat Luong Nuoc Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody MauChatLuongNuocAoNuoi MauChatLuongNuocAoNuoi) {
        try {
            MauChatLuongNuocAoNuoi.setId(id);
            log.info("Updating Mau Chat Luong Nuoc Ao Nuoi [{}] ...", MauChatLuongNuocAoNuoi.toString());
            MauChatLuongNuocAoNuoi entityUpdated = this.mauChatLuongNuocAoNuoiService.update(MauChatLuongNuocAoNuoi);
            log.info("Updated Mau Chat Luong Nuoc Ao Nuoi [{}]", entityUpdated.getId());
            return ResponseEntity.ok(entityUpdated);
        } catch (Exception e) {
            log.error("Cannot update Mau Chat Luong Nuoc Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> remove(
            @NotNull @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        try {
            log.info("Removing Mau Chat Luong Nuoc Ao Nuoi [{}] with user [{}] ...", id, user);
            this.mauChatLuongNuocAoNuoiService.remove(id, user);
            log.info("Removed Mau Chat Luong Nuoc Ao Nuoi [{}]", id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed Mau Chat Luong Nuoc Ao Nuoi [" + id + "] successfully!");
        } catch (Exception e) {
            log.error("Cannot remove Mau Chat Luong Nuoc Ao Nuoi: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
