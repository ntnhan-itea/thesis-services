package com.edu.ctu.thesis.seafood.thagiong;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class ThaGiongService {

    @Autowired
    ThaGiongRepository thaGiongRepository;

    @Autowired
    UserService userService;

    public ThaGiong create(ThaGiong thaGiong) {
        this.isNotExistNhatKyInDB(thaGiong.getNhatKy().getId());
        return this.thaGiongRepository.save(thaGiong);
    }

    private void isNotExistNhatKyInDB(Long nhatKyId) {
        ThaGiong existingThaGiong = this.findByNhatKyId(nhatKyId);
        if (existingThaGiong != null) {
            throw new IllegalArgumentException(
                    "Nhat Ky [" + nhatKyId + "] is already exist with relationship Tha Giong [" + existingThaGiong.getId()
                            + "]");
        }
    }

    public ThaGiong update(ThaGiong thaGiong) {
        ThaGiong thaGiongInDB = this.findById(thaGiong.getId(), thaGiong.getUser());
        thaGiongInDB.copy(thaGiong);
        return this.thaGiongRepository.save(thaGiongInDB);
    }

    public void remove(Long id, User user) {
        ThaGiong thaGiong = this.findById(id, user);
        thaGiong.getNhatKy().setThaGiong(null);
        this.thaGiongRepository.delete(thaGiong);
    }

    public ThaGiong findById(Long id) {
        Optional<ThaGiong> thaGiong = this.thaGiongRepository.findById(id);
        if (!thaGiong.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Tha Giong [" + id + "] trong DB");
        }
        return thaGiong.get();
    }

    public ThaGiong findById(Long id, User user) {
        ThaGiong ThaGiong = this.findById(id);
        this.userService.checkLoginSucceed(user, ThaGiong.getUser());
        return ThaGiong;
    }

    public ThaGiong findByNhatKyId(Long nhatKyId) {
        ThaGiong ThaGiong = this.thaGiongRepository.findByNhatKyId(nhatKyId);
        return ThaGiong;
    }

}
