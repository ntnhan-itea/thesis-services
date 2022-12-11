package com.edu.ctu.thesis.seafood.vungnuoi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoiService;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class VungNuoiService {

    @Autowired
    VungNuoiRepository vungNuoiRepository;

    @Autowired
    TraiNuoiService traiNuoiService;

    @Autowired
    UserService userService;

    public VungNuoi createVungNuoi(VungNuoi vungNuoi) {
        return this.save(vungNuoi);
    }

    public VungNuoi updateVungNuoi(VungNuoi vungNuoi) {
        VungNuoi vungNuoiInDB = this.findById(vungNuoi.getId());

        // this.userService.checkLoginSucceed(vungNuoi.getUser(), vungNuoiInDB.getUser());

        vungNuoiInDB.copy(vungNuoi);
        return this.save(vungNuoiInDB);
    }

    private VungNuoi save(VungNuoi vungNuoi) {
        vungNuoi.convertListPointsToString();
        return this.vungNuoiRepository.save(vungNuoi);
    }

    private VungNuoi findById(Long id) {
        Optional<VungNuoi> vungNuoi = this.vungNuoiRepository.findById(id);
        if (!vungNuoi.isPresent()) {
            throw new IllegalArgumentException("Vung nuoi [" + id + "] khong tim thay trong DB!");
        }
        return vungNuoi.get();
    }

    public VungNuoi findByIdAndUser(Long id, User user) {
        VungNuoi vungNuoi = this.findById(id);
        this.userService.checkLoginSucceed(user, vungNuoi.getUser());
        return vungNuoi;
    }

    public void removeVungNuoi(Long id, User user) {
        TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
        List<VungNuoi> vungNuoisInDB = traiNuoiInDB.getVungNuois();
        if (CollectionUtils.isEmpty(vungNuoisInDB)) {
            throw new IllegalArgumentException("Khong tim thay danh sach vung nuoi trong trai nuoi!");
        }
        VungNuoi vungNuoiInDB = this.findById(id);
        vungNuoisInDB.stream().filter(e -> vungNuoiInDB.getId().equals(e.getId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find vung nuoi [" + id + "] in your DB"));

        vungNuoiInDB.getTraiNuoi().setVungNuois(null);
        this.vungNuoiRepository.delete(vungNuoiInDB);
    }

}
