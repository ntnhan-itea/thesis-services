package com.edu.ctu.thesis.seafood.ketquathuhoach;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class KetQuaThuHoachService {
    
    @Autowired
    KetQuaThuHoachRepository ketQuaThuHoachRepository;

    @Autowired
    UserService userService;

    public KetQuaThuHoach create(KetQuaThuHoach ketQuaThuHoach) {
        Long nhatKyId = ketQuaThuHoach.getNhatKy().getId();
        Optional<KetQuaThuHoach> existingEntity = this.findByNhatKyId(nhatKyId);
        if(existingEntity.isPresent()) {
            throw new IllegalArgumentException("Nhat ky [" + nhatKyId
                    + "] is already exist with relationship Ket Qua Thu Hoach [" + existingEntity.get().getId() + "]");
        }

        return this.ketQuaThuHoachRepository.save(ketQuaThuHoach);
    }

    public Optional<KetQuaThuHoach> findByNhatKyId(Long nhatKyId) {
        return this.ketQuaThuHoachRepository.findByNhatKyId(nhatKyId);
    }

    public KetQuaThuHoach update(KetQuaThuHoach ketQuaThuHoach) {
        KetQuaThuHoach entityInDB = this.findById(ketQuaThuHoach.getId(), ketQuaThuHoach.getUser());
        entityInDB.copy(ketQuaThuHoach);
        return this.ketQuaThuHoachRepository.save(entityInDB);
    }

    public void remove(Long id, User user) {
        KetQuaThuHoach entityInDB = this.findById(id, user);
        entityInDB.getNhatKy().setKetQuaThuHoach(null);
        this.ketQuaThuHoachRepository.delete(entityInDB);
    }

    public KetQuaThuHoach findById(Long id) {
        Optional<KetQuaThuHoach> ketQuaThuHoach = this.ketQuaThuHoachRepository.findById(id);
        if (ketQuaThuHoach.isEmpty()) {
            throw new IllegalArgumentException("Khong tim thay Ket Qua Thu Hoach [" + id + "] trong DB!");
        }
        return ketQuaThuHoach.get();
    }

    public KetQuaThuHoach findById(Long id, User user) {
        KetQuaThuHoach ketQuaThuHoach = this.findById(id);
        this.userService.checkLoginSucceed(user, ketQuaThuHoach.getUser());
        return ketQuaThuHoach;
    }

}
