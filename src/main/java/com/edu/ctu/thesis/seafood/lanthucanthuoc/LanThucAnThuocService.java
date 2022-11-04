package com.edu.ctu.thesis.seafood.lanthucanthuoc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class LanThucAnThuocService {
    
    @Autowired
    LanThucAnThuocRepository lanThucAnThuocRepository;

    @Autowired
    UserService userService;

    public LanThucAnThuoc create(LanThucAnThuoc entity) {
        return this.lanThucAnThuocRepository.save(entity);
    }

    public LanThucAnThuoc update(LanThucAnThuoc entity) {
        LanThucAnThuoc entityInDB = this.findById(entity.getId(), entity.getUser());
        entityInDB.copy(entity);
        return this.lanThucAnThuocRepository.save(entityInDB);
    }

    public void remove(Long id, User user) {
        LanThucAnThuoc entityInDB = this.findById(id, user);
        entityInDB.getNhatKy().setLanThucAnThuocs(null);
        this.lanThucAnThuocRepository.delete(entityInDB);
    }

    public LanThucAnThuoc findById(Long id) {
        Optional<LanThucAnThuoc> entityOptional = this.lanThucAnThuocRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Lan Thuc An Thuoc [" + id + "] trong DB");
        }
        return entityOptional.get();
    }

    public LanThucAnThuoc findById(Long id, User user) {
        LanThucAnThuoc entityInDB = this.findById(id);
        this.userService.checkLoginSucceed(user, entityInDB.getUser());
        return entityInDB;
    }

}
