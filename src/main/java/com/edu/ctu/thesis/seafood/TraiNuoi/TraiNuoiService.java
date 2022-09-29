package com.edu.ctu.thesis.seafood.TraiNuoi;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserRepository;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class TraiNuoiService {

    public static final String INVALID_ACCOUNT = "Invalid username or password";

    @Autowired
    TraiNuoiRepository traiNuoiRepository;

    @Autowired
    UserRepository userRepository;

    public TraiNuoi createTraiNuoi(TraiNuoi traiNuoi) {
        User user = traiNuoi.getUser();
        user.setTraiNuoi(traiNuoi);

        User userInDB = this.userRepository.findByUsername(user.getUsername());
        if (Objects.nonNull(userInDB)) {
            throw new IllegalArgumentException("Username [" + user.getUsername() + "] is already exist in Database");
        }
        String password = ThesisUtils.encodeBase64(user.getPassword().trim());
        user.setPassword(password);

        return this.traiNuoiRepository.save(traiNuoi);
    }

    public TraiNuoi updateTraiNuoi(TraiNuoi traiNuoi) {
        String username = traiNuoi.getUser() == null ? null : traiNuoi.getUser().getUsername();
        String password = traiNuoi.getUser() == null ? null : traiNuoi.getUser().getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }
        username = username.trim();
        password = ThesisUtils.encodeBase64(password.trim());

        TraiNuoi traiNuoiInDB = this.findById(traiNuoi.getId());
        String usernameInDB = traiNuoiInDB.getUser().getUsername();
        String passwordInDB = traiNuoiInDB.getUser().getPassword();

        if (!username.equals(usernameInDB) || !password.equals(passwordInDB)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        traiNuoiInDB.copy(traiNuoi);
        return this.traiNuoiRepository.save(traiNuoiInDB);
    }

    public TraiNuoi findById(Long id) {
        Optional<TraiNuoi> traiNuoiInDB = this.traiNuoiRepository.findById(id);
        if (!traiNuoiInDB.isPresent()) {
            throw new IllegalArgumentException("Khong tim thay trai nuoi trong DB [" + id + "]");
        }
        return traiNuoiInDB.get();
    }

    public TraiNuoi getTraiNuoi(TraiNuoi traiNuoi) {
        String username = traiNuoi.getUser().getUsername();
        String password = traiNuoi.getUser().getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        username = username.trim();
        password = ThesisUtils.encodeBase64(password.trim());

        TraiNuoi traiNuoiInDB = this.traiNuoiRepository.findByAccount(username, password);
        if (Objects.isNull(traiNuoiInDB)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        return traiNuoiInDB;
    }
}
