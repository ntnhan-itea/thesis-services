package com.edu.ctu.thesis.seafood.user;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        String username = user.getUsername().trim();

        User userInDB = this.userRepository.findByUsername(username);
        if (Objects.nonNull(userInDB)) {
            throw new IllegalArgumentException("Username [" + username + "] is already exist in Database");
        }

        String password = ThesisUtils.encodeBase64(user.getPassword().trim());

        user.setUsername(username);
        user.setPassword(password);

        TraiNuoi traiNuoi = user.getTraiNuoi();
        traiNuoi.setUser(user);
        return this.userRepository.save(user);
    }

    public User getUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        username = username.trim();
        password = ThesisUtils.encodeBase64(password.trim());

        User userInDB = this.userRepository.findByAccount(username, password);
        if (Objects.isNull(userInDB)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return userInDB;
    }

}
