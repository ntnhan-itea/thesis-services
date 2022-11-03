package com.edu.ctu.thesis.seafood.lantheodoitangtruong;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class LanTheoDoiTangTruongService {
    
    @Autowired
    LanTheoDoiTangTruongRepository lanTheoDoiTangTruongRepository;

    @Autowired
    UserService userService;

    public LanTheoDoiTangTruong create(LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        return this.lanTheoDoiTangTruongRepository.save(lanTheoDoiTangTruong);
    }

    public LanTheoDoiTangTruong update(LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        LanTheoDoiTangTruong lanTheoDoiTangTruongInDB = this.findById(lanTheoDoiTangTruong.getId(), lanTheoDoiTangTruong.getUser());
        lanTheoDoiTangTruongInDB.copy(lanTheoDoiTangTruong);
        return this.lanTheoDoiTangTruongRepository.save(lanTheoDoiTangTruongInDB);
    }

    public void remove(Long id, User user) {
        LanTheoDoiTangTruong lanTheoDoiTangTruongInDB = this.findById(id, user);
        lanTheoDoiTangTruongInDB.getNhatKy().setLanTheoDoiTangTruongs(null);
        this.lanTheoDoiTangTruongRepository.delete(lanTheoDoiTangTruongInDB);
    }

    public LanTheoDoiTangTruong findById(Long id) {
        Optional<LanTheoDoiTangTruong> lanTheoDoiTangTruong = this.lanTheoDoiTangTruongRepository.findById(id);
        if (!lanTheoDoiTangTruong.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Lan Theo Doi Tang Truong [" + id + "] trong DB");
        }
        return lanTheoDoiTangTruong.get();
    }

    public LanTheoDoiTangTruong findById(Long id, User user) {
        LanTheoDoiTangTruong LanTheoDoiTangTruong = this.findById(id);
        this.userService.checkLoginSucceed(user, LanTheoDoiTangTruong.getUser());
        return LanTheoDoiTangTruong;
    }
}
