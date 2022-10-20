package com.edu.ctu.thesis.seafood.TraiNuoi;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class TraiNuoiService {

    public static final String INVALID_ACCOUNT = "Invalid username or password";

    @Autowired
    TraiNuoiRepository traiNuoiRepository;

    @Autowired
    UserService userService;

    public TraiNuoi createTraiNuoi(TraiNuoi traiNuoi) {
        User user = traiNuoi.getUser();
        this.userService.checkUserIsNotExistInDb(user);
        user.setTraiNuoi(traiNuoi);
        String username = user.getUsername().trim();
        String password = ThesisUtils.encodeBase64(user.getPassword().trim());
        user.setUsername(username);
        user.setPassword(password);

        List<VungNuoi> vungNuois = traiNuoi.getVungNuois();
        if (!CollectionUtils.isEmpty(vungNuois)) {
            vungNuois.stream().filter(VungNuoi::isValid).forEach(e -> {
                e.setTraiNuoi(traiNuoi);
                e.setTenVungNuoi(e.getTenVungNuoi().trim());
            });
        }

        return this.traiNuoiRepository.save(traiNuoi);
    }

    public TraiNuoi updateTraiNuoi(TraiNuoi traiNuoi) {
        TraiNuoi traiNuoiInDB = this.findById(traiNuoi.getId());

        User userLogin = traiNuoi.getUser();
        User userInDB = traiNuoiInDB.getUser();
        this.userService.checkLoginSucceed(userLogin, userInDB);

        traiNuoiInDB.copy(traiNuoi);
        return this.traiNuoiRepository.save(traiNuoiInDB);
    }

    public void remove(Long id) {
        this.traiNuoiRepository.delete(this.findById(id));
    }

    public TraiNuoi findById(Long id) {
        Optional<TraiNuoi> traiNuoiInDB = this.traiNuoiRepository.findById(id);
        if (!traiNuoiInDB.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay trai nuoi trong DB [" + id + "]");
        }
        return traiNuoiInDB.get();
    }

    public TraiNuoi getTraiNuoi(User user) {
        this.userService.checkValidUser(user);
        String username = user.getUsername().trim();
        String password = ThesisUtils.encodeBase64(user.getPassword().trim());

        TraiNuoi traiNuoiInDB = this.traiNuoiRepository.findByAccount(username, password);
        if (Objects.isNull(traiNuoiInDB)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        return traiNuoiInDB;
    }

    public TraiNuoi updateVungNuoi(List<VungNuoi> vungNuois) {
        User user = vungNuois.stream()
                .filter(Objects::nonNull).filter(e -> e.getUser() != null)
                .map(VungNuoi::getUser)
                .findFirst().orElse(null);

        TraiNuoi traiNuoiInDB = this.getTraiNuoi(user);
        List<VungNuoi> vungNuoisInDB = traiNuoiInDB.getVungNuois();

        if (CollectionUtils.isEmpty(vungNuoisInDB)) {
            throw new IllegalArgumentException("Khong tim thay danh sach vung nuoi trong DB!");
        }

        for (VungNuoi vungNuoi : vungNuois) {
            VungNuoi vungNuoiInDB = vungNuoisInDB.stream()
                    .filter(e -> e.getId().equals(vungNuoi.getId()))
                    .findFirst().orElse(null);
            if (vungNuoiInDB != null) {
                vungNuoiInDB.copy(vungNuoi);
            }
        }

        return this.traiNuoiRepository.save(traiNuoiInDB);
    }
}
