package com.edu.ctu.thesis.seafood.user;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class UserService {

    public static final String INVALID_ACCOUNT = "Invalid username or password";

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        this.checkUserIsNotExistInDb(user);
        String username = user.getUsername().trim();
        String password = ThesisUtils.encodeBase64(user.getPassword().trim());

        user.setUsername(username);
        user.setPassword(password);

        TraiNuoi traiNuoi = user.getTraiNuoi();
        traiNuoi.setUser(user);
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        User userInDB = this.getUser(user);
        this.isValidNewPassword(user);

        userInDB.copy(user);
        return this.userRepository.save(userInDB);
    }

    private void isValidNewPassword(User user) {
        if(user == null) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        if(StringUtils.isBlank(user.getNewPassword())) {
            throw new IllegalArgumentException("Invalid new password");
        }
    }

    public void checkUserIsNotExistInDb(User user) {
        this.checkValidUser(user);
        String username = user.getUsername().trim();

        User userInDB = this.userRepository.findByUsername(username);
        if (Objects.nonNull(userInDB)) {
            throw new IllegalArgumentException("Username [" + username + "] is already exist in Database");
        }
    }

    public User getUser(User user) {
        this.checkValidUser(user);
        String username = user.getUsername().trim();
        String password = ThesisUtils.encodeBase64(user.getPassword().trim());

        User userInDB = this.userRepository.findByAccount(username, password);
        if (Objects.isNull(userInDB)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        return userInDB;
    }

    public void checkLoginSucceed(User userLogin, User userInDB) {
        this.checkValidUser(userLogin);
        this.checkValidUser(userInDB);

        String usernameLogin = userLogin.getUsername().trim();
        String passwordLogin = ThesisUtils.encodeBase64(userLogin.getPassword().trim());

        String usernameInDB = userInDB.getUsername().trim();
        String passwordInDB = userInDB.getPassword().trim();

        if (!usernameLogin.equals(usernameInDB) || !passwordLogin.equals(passwordInDB)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }
    }

    public void checkValidUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }
    }

}
