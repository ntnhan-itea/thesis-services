package com.edu.ctu.thesis.seafood.aonuoi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoiService;

@Service
public class AoNuoiService {

    @Autowired
    AoNuoiRepository aoNuoiRepository;

    @Autowired
    UserService userService;

    @Autowired
    VungNuoiService vungNuoiService;

    public AoNuoi createAoNuoi(Long vungNuoiId, AoNuoi aoNuoi) {
        User user = aoNuoi.getUser();
        VungNuoi vungNuoiInDB = this.vungNuoiService.findByIdAndUser(vungNuoiId, user);
        aoNuoi.setVungNuoi(vungNuoiInDB);
        aoNuoi.setUser(vungNuoiInDB.getUser());
        return this.aoNuoiRepository.save(aoNuoi);
    }

    public AoNuoi updateAoNuoi(AoNuoi aoNuoi) {
        AoNuoi aoNuoiInDB = this.findById(aoNuoi.getId());
        this.userService.checkLoginSucceed(aoNuoi.getUser(), aoNuoiInDB.getUser());
        aoNuoiInDB.copy(aoNuoi);
        return this.aoNuoiRepository.save(aoNuoiInDB);
    }

    public AoNuoi findById(Long id) {
        Optional<AoNuoi> aoNuoi = this.aoNuoiRepository.findById(id);
        if (!aoNuoi.isPresent()) {
            throw new IllegalArgumentException("Cannot find Ao Nuoi [" + id + "] in DB");
        }
        return aoNuoi.get();
    }

    public void removeAoNuoi(Long id, User user) {
        AoNuoi aoNuoiInDB = this.findById(id);
        this.userService.checkLoginSucceed(user, aoNuoiInDB.getUser());
        this.aoNuoiRepository.delete(aoNuoiInDB);
    }

}
