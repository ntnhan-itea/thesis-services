package com.edu.ctu.thesis.seafood.user;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        String username = user.getValidUsername();
        String password = user.getEncodedPassword();

        user.setUsername(username);
        user.setPassword(password);

        TraiNuoi traiNuoi = user.getTraiNuoi();
        if(traiNuoi == null) {
            traiNuoi = new TraiNuoi();
        }
        traiNuoi.setTenTraiNuoi("UnKnown Trai Nuoi name");
        traiNuoi.setUser(user);
        return this.userRepository.save(user);
    }

    public User createUser(TraiNuoi traiNuoi) {
        User user = traiNuoi.getUser();
        this.checkUserIsNotExistInDb(user);
        String username = user.getValidUsername();
        String password = user.getEncodedPassword();

        user.setUsername(username);
        user.setPassword(password);
        user.setTraiNuoi(traiNuoi);

        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        User userInDB = this.getUser(user);
        this.isValidNewPassword(user);

        userInDB.copy(user);
        return this.userRepository.save(userInDB);
    }

    private void isValidNewPassword(User user) {
        if (user == null) {
            throw new IllegalArgumentException(INVALID_ACCOUNT);
        }

        if (StringUtils.isBlank(user.getNewPassword())) {
            throw new IllegalArgumentException("Invalid new password");
        }
    }

    public void checkUserIsNotExistInDb(User user) {
        this.checkValidUser(user);
        String username = user.getValidUsername();

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

    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "username"));
    }

    public void checkLoginSucceed(User userLogin, User userInDB) {
        this.checkValidUser(userLogin);
        this.checkValidUser(userInDB);

        String usernameLogin = userLogin.getValidUsername();
        String passwordLogin = userLogin.getEncodedPassword();

        String usernameInDB = userInDB.getValidUsername();
        String passwordInDB = userInDB.getValidPassword();

        if (!usernameLogin.equalsIgnoreCase(usernameInDB) || !passwordLogin.equals(passwordInDB)) {
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
