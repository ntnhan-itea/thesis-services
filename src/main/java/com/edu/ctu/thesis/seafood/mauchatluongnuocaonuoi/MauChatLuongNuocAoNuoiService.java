package com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class MauChatLuongNuocAoNuoiService {
    
    @Autowired
    MauChatLuongNuocAoNuoiRepository mauChatLuongNuocAoNuoiRepository;

    @Autowired
    UserService userService;

    public MauChatLuongNuocAoNuoi create(MauChatLuongNuocAoNuoi entity) {
        return this.mauChatLuongNuocAoNuoiRepository.save(entity);
    }

    public MauChatLuongNuocAoNuoi update(MauChatLuongNuocAoNuoi mauChatLuongNuocAoNuoi) {
        MauChatLuongNuocAoNuoi MauChatLuongNuocAoNuoiInDB = this.findById(mauChatLuongNuocAoNuoi.getId(), mauChatLuongNuocAoNuoi.getUser());
        MauChatLuongNuocAoNuoiInDB.copy(mauChatLuongNuocAoNuoi);
        return this.mauChatLuongNuocAoNuoiRepository.save(MauChatLuongNuocAoNuoiInDB);
    }

    public void remove(Long id, User user) {
        MauChatLuongNuocAoNuoi MauChatLuongNuocAoNuoiInDB = this.findById(id, user);
        MauChatLuongNuocAoNuoiInDB.getNhatKy().setMauChatLuongNuocAoNuois(null);
        this.mauChatLuongNuocAoNuoiRepository.delete(MauChatLuongNuocAoNuoiInDB);
    }
    public MauChatLuongNuocAoNuoi findById(Long id) {
        Optional<MauChatLuongNuocAoNuoi> MauChatLuongNuocAoNuoi = this.mauChatLuongNuocAoNuoiRepository.findById(id);
        if (!MauChatLuongNuocAoNuoi.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay Mau Chat Luong Nuoc Ao Nuoi [" + id + "] trong DB");
        }
        return MauChatLuongNuocAoNuoi.get();
    }

    public MauChatLuongNuocAoNuoi findById(Long id, User user) {
        MauChatLuongNuocAoNuoi MauChatLuongNuocAoNuoi = this.findById(id);
        this.userService.checkLoginSucceed(user, MauChatLuongNuocAoNuoi.getUser());
        return MauChatLuongNuocAoNuoi;
    }

}
