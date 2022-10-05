package com.edu.ctu.thesis.seafood.nhatky;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoiService;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class NhatKyService {

    @Autowired
    NhatKyRepository nhatKyRepository;

    @Autowired
    AoNuoiService aoNuoiService;

    @Autowired
    UserService userService;

    public NhatKy createNhatKy(Long aoNuoiId, NhatKy nhatKy) {
        AoNuoi aoNuoiInDB = this.aoNuoiService.findByIdAndUser(aoNuoiId, nhatKy.getUser());

        nhatKy.setAoNuoi(aoNuoiInDB);
        nhatKy.setUser(aoNuoiInDB.getUser());
        return this.nhatKyRepository.save(nhatKy);
    }

    public NhatKy updateNhatKy(Long id, NhatKy nhatKy) {
        NhatKy nhatKyInDB = this.findById(id);
        this.userService.checkLoginSucceed(nhatKy.getUser(), nhatKyInDB.getUser());
        nhatKyInDB.copy(nhatKy);
        return this.nhatKyRepository.save(nhatKyInDB);
    }

    public void removeNhatKy(Long id, User user) {
        NhatKy nhatKyInDB = this.findById(id);
        this.userService.checkLoginSucceed(user, nhatKyInDB.getUser());
        this.nhatKyRepository.delete(nhatKyInDB);
    }

    public NhatKy findById(Long id) {
        Optional<NhatKy> nhatKy = this.nhatKyRepository.findById(id);
        if (!nhatKy.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Nhat Ky [" + id + "] trong DB");
        }
        return nhatKy.get();
    }

}
