package com.edu.ctu.thesis.seafood.aonuoi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.exceptions.EntityNotFound;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoiService;

@Service
public class AoNuoiService {

    @Autowired
    AoNuoiRepository aoNuoiRepository;

    @Autowired
    UserService userService;

    @Autowired
    VungNuoiService vungNuoiService;


    public AoNuoi createAoNuoi(AoNuoi aoNuoi) {
        return this.save(aoNuoi);
    }

    public AoNuoi updateAoNuoi(AoNuoi aoNuoi) {
        AoNuoi aoNuoiInDB = this.findById(aoNuoi.getId());
        this.userService.checkLoginSucceed(aoNuoi.getUser(), aoNuoiInDB.getUser());
        aoNuoiInDB.copy(aoNuoi);
        return this.save(aoNuoiInDB);
    }

    private AoNuoi save(AoNuoi aoNuoi) {
        aoNuoi.convertListPointsToString();
        return this.aoNuoiRepository.save(aoNuoi);
    }

    private AoNuoi findById(Long id) {
        Optional<AoNuoi> aoNuoi = this.aoNuoiRepository.findById(id);
        if (!aoNuoi.isPresent()) {
            throw new EntityNotFound("Cannot find Ao Nuoi [" + id + "] in DB");
        }
        return aoNuoi.get();
    }

    public void removeAoNuoi(Long id, User user) {
        AoNuoi aoNuoiInDB = this.findByIdAndUser(id, user);
        aoNuoiInDB.getVungNuoi().setAoNuois(null);
        this.aoNuoiRepository.delete(aoNuoiInDB);
    }

    public AoNuoi findByIdAndUser(Long id, User user) {
        AoNuoi aoNuoi = this.findById(id);
        this.userService.checkLoginSucceed(user, aoNuoi.getUser());
        return aoNuoi;
    }

}
