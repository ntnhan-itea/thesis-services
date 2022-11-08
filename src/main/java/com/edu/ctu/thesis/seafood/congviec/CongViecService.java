package com.edu.ctu.thesis.seafood.congviec;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class CongViecService {
    
    @Autowired
    CongViecRepository congViecRepository;

    @Autowired
    UserService userService;

    public CongViec create(CongViec entity) {
        return this.congViecRepository.save(entity);
    }

    public CongViec update(CongViec congViec) {
        CongViec CongViecInDB = this.findById(congViec.getId(), congViec.getUser());
        CongViecInDB.copy(congViec);
        return this.congViecRepository.save(CongViecInDB);
    }

    public void remove(Long id, User user) {
        CongViec CongViecInDB = this.findById(id, user);
        CongViecInDB.getQuyTrinh().setCongViecs(null);
        this.congViecRepository.delete(CongViecInDB);
    }
    public CongViec findById(Long id) {
        Optional<CongViec> CongViec = this.congViecRepository.findById(id);
        if (!CongViec.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Cong Viec [" + id + "] trong DB");
        }
        return CongViec.get();
    }

    public CongViec findById(Long id, User user) {
        CongViec CongViec = this.findById(id);
        this.userService.checkLoginSucceed(user, CongViec.getUser());
        return CongViec;
    }

}
