package com.edu.ctu.thesis.seafood.thanhphancaitao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class ThanhPhanCaiTaoService {

    @Autowired
    ThanhPhanCaiTaoRepository thanhPhanCaiTaoRepository;

    @Autowired
    UserService userService;

    public ThanhPhanCaiTao create(ThanhPhanCaiTao thanhPhanCaiTao) {
        return this.thanhPhanCaiTaoRepository.save(thanhPhanCaiTao);
    }

    public ThanhPhanCaiTao update(ThanhPhanCaiTao thanhPhanCaiTao) {
        ThanhPhanCaiTao thanhPhanCaiTaoInDB = this.findById(thanhPhanCaiTao.getId(), thanhPhanCaiTao.getUser());
        thanhPhanCaiTaoInDB.copy(thanhPhanCaiTao);
        return this.thanhPhanCaiTaoRepository.save(thanhPhanCaiTaoInDB);
    }

    public void remove(Long id, User user) {
        ThanhPhanCaiTao thanhPhanCaiTaoInDB = this.findById(id, user);
        thanhPhanCaiTaoInDB.getChuanBiAoNuoi().setThanhPhanCaiTaos(null);
        this.thanhPhanCaiTaoRepository.delete(thanhPhanCaiTaoInDB);
    }

    public ThanhPhanCaiTao findById(Long id) {
        Optional<ThanhPhanCaiTao> thanhPhanCaiTao = this.thanhPhanCaiTaoRepository.findById(id);
        if (thanhPhanCaiTao.isEmpty()) {
            throw new IllegalArgumentException("Cannot find Thanh Phan Cai Tao [" + id + "] in DB");
        }
        return thanhPhanCaiTao.get();
    }

    public ThanhPhanCaiTao findById(Long id, User user) {
        ThanhPhanCaiTao thanhPhanCaiTao = this.findById(id);
        this.userService.checkLoginSucceed(user, thanhPhanCaiTao.getUser());
        return thanhPhanCaiTao;
    }

}
