package com.edu.ctu.thesis.seafood.quytrinh;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class QuyTrinhService {

    @Autowired
    QuyTrinhRepository quyTrinhRepository;

    @Autowired
    UserService userService;

    public QuyTrinh create(QuyTrinh entity) {
        Long nhatKyId = entity.getNhatKy().getId();
        Optional<QuyTrinh> quyTrinhInDB = this.existsByNhatKyId(nhatKyId);
        if (quyTrinhInDB.isPresent()) {
            throw new IllegalArgumentException(
                    "Nhat Ky [" + nhatKyId + "] is already exist with relationship Quy Trinh ["
                            + quyTrinhInDB.get().getId()
                            + "]");
        }

        return this.quyTrinhRepository.save(entity);
    }

    public Optional<QuyTrinh> existsByNhatKyId(Long nhatKyId) {
        return this.quyTrinhRepository.existsByNhatKyId(nhatKyId);
    }

    public QuyTrinh update(QuyTrinh quyTrinh) {
        QuyTrinh entityInDB = this.findById(quyTrinh.getId(), quyTrinh.getUser());
        entityInDB.copy(quyTrinh);
        return this.quyTrinhRepository.save(entityInDB);
    }

    public void remove(Long id, User user) {
        QuyTrinh entityInDB = this.findById(id, user);
        entityInDB.getNhatKy().setQuyTrinh(null);
        this.quyTrinhRepository.delete(entityInDB);
    }

    public QuyTrinh findById(Long id) {
        Optional<QuyTrinh> entityOptional = this.quyTrinhRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Quy Trinh [" + id + "] trong DB");
        }
        return entityOptional.get();
    }

    public QuyTrinh findById(Long id, User user) {
        QuyTrinh entity = this.findById(id);
        this.userService.checkLoginSucceed(user, entity.getUser());
        return entity;
    }

}
