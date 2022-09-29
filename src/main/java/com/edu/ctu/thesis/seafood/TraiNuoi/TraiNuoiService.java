package com.edu.ctu.thesis.seafood.TraiNuoi;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserRepository;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class TraiNuoiService {
    
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

    public TraiNuoi getTraiNuoi(TraiNuoi traiNuoi) {
        String username = traiNuoi.getUser().getUsername();
        String password = traiNuoi.getUser().getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        username = username.trim();
        password = ThesisUtils.encodeBase64(password.trim());

        TraiNuoi traiNuoiInDB = this.traiNuoiRepository.findByAccount(username, password);
        if (Objects.isNull(traiNuoiInDB)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return traiNuoiInDB;
    }
}
