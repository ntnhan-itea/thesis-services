package com.edu.ctu.thesis.seafood.thuhoachvadoanhthu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class ThuHoachVaDoanhThuService {
    
    @Autowired
    ThuHoachVaDoanhThuRepository thuHoachVaDoanhThuRepository;

    @Autowired
    UserService userService;

    public ThuHoachVaDoanhThu create(ThuHoachVaDoanhThu entity) {
        return this.thuHoachVaDoanhThuRepository.save(entity);
    }

    public ThuHoachVaDoanhThu update(ThuHoachVaDoanhThu entity) {
        ThuHoachVaDoanhThu entityInDB = this.findById(entity.getId(), entity.getUser());
        entityInDB.copy(entity);
        return this.thuHoachVaDoanhThuRepository.save(entityInDB);
    }

    public void remove(Long id, User user) {
        ThuHoachVaDoanhThu entityInDB = this.findById(id, user);
        entityInDB.getKetQuaThuHoach().setThuHoachVaDoanhThus(null);
        this.thuHoachVaDoanhThuRepository.delete(entityInDB);
    }

    public ThuHoachVaDoanhThu findById(Long id) {
        Optional<ThuHoachVaDoanhThu> entityOptional = this.thuHoachVaDoanhThuRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new IllegalArgumentException("Cannot find Thu Hoach va Doanh Thu [" + id + "] in DB");
        }
        return entityOptional.get();
    }

    public ThuHoachVaDoanhThu findById(Long id, User user) {
        ThuHoachVaDoanhThu entityInDB = this.findById(id);
        this.userService.checkLoginSucceed(user, entityInDB.getUser());
        return entityInDB;
    }

}
