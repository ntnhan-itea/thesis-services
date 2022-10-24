package com.edu.ctu.thesis.seafood.nhatky;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.aonuoi.AoNuoiService;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoiService;
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

    @Autowired
    ChuanBiAoNuoiService chuanBiAoNuoiService;

    public NhatKy create(NhatKy nhatKy) {
        return this.nhatKyRepository.save(nhatKy);
    }

    public NhatKy update(Long id, NhatKy nhatKy) {
        NhatKy nhatKyInDB = this.findById(id);
        this.userService.checkLoginSucceed(nhatKy.getUser(), nhatKyInDB.getUser());
        nhatKyInDB.copy(nhatKy);
        return this.nhatKyRepository.save(nhatKyInDB);
    }

    public void remove(Long id, User user) {
        NhatKy nhatKyInDB = this.findById(id, user);
        nhatKyInDB.getAoNuoi().setListOfNhatKy(null);
        this.nhatKyRepository.delete(nhatKyInDB);
    }

    public NhatKy findById(Long id) {
        Optional<NhatKy> nhatKy = this.nhatKyRepository.findById(id);
        if (!nhatKy.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Nhat Ky [" + id + "] trong DB");
        }
        return nhatKy.get();
    }

    public NhatKy findById(Long id, User user) {
        NhatKy nhatKy = this.findById(id);
        this.userService.checkLoginSucceed(user, nhatKy.getUser());
        return nhatKy;
    }

}
