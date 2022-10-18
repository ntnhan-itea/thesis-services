package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class ChuanBiAoNuoiService {

    private final ChuanBiAoNuoiRepository chuanBiAoNuoiRepository;
    private final UserService userService;

    public ChuanBiAoNuoiService(ChuanBiAoNuoiRepository chuanBiAoNuoiRepository,
            UserService userService) {
        this.chuanBiAoNuoiRepository = chuanBiAoNuoiRepository;
        this.userService = userService;
    }

    public ChuanBiAoNuoi createChuanBiAoNuoi(ChuanBiAoNuoi chuanBiAoNuoi) {
        return this.chuanBiAoNuoiRepository.save(chuanBiAoNuoi);
    }

    public ChuanBiAoNuoi updateChuanBiAoNuoi(ChuanBiAoNuoi chuanBiAoNuoi) {
        ChuanBiAoNuoi chuanBiAoNuoiInDB = this.findById(chuanBiAoNuoi.getId());
        this.userService.checkLoginSucceed(chuanBiAoNuoi.getUser(), chuanBiAoNuoiInDB.getUser());
        chuanBiAoNuoiInDB.copy(chuanBiAoNuoi);
        return this.chuanBiAoNuoiRepository.save(chuanBiAoNuoiInDB);
    }

    public ChuanBiAoNuoi findById(Long id) {
        Optional<ChuanBiAoNuoi> chuanBiAoNuoi = this.chuanBiAoNuoiRepository.findById(id);
        if (!chuanBiAoNuoi.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Chuan Bi Ao Nuoi [" + id + "] trong DB!");
        }
        return chuanBiAoNuoi.get();
    }

    public ChuanBiAoNuoi findById(Long id, User user) {
        ChuanBiAoNuoi chuanBiAoNuoi = this.findById(id);
        this.userService.checkLoginSucceed(user, chuanBiAoNuoi.getUser());
        return chuanBiAoNuoi;
    }

    public void removeById(Long id, User user) {
        ChuanBiAoNuoi chuanBiAoNuoi = this.findById(id, user);
        chuanBiAoNuoi.getNhatKy().setChuanBiAoNuoi(null);
        this.chuanBiAoNuoiRepository.delete(chuanBiAoNuoi);
    }

}
