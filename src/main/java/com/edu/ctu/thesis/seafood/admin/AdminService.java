package com.edu.ctu.thesis.seafood.admin;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.exceptions.EntityAlreadyExistException;
import com.edu.ctu.thesis.exceptions.InvalidAccountException;
import com.edu.ctu.thesis.exceptions.StringBlankException;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;

@Service
public class AdminService {

    public static final String INVALID_ACCOUNT = "Invalid username or password";

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;
    
    public AdminUser create(AdminUser adminUser) {
        this.checkValidUser(adminUser);
        String username = adminUser.getValidUsername();
        String password = adminUser.getEncodedPassword();

        if(this.isExistUsername(username)) {
            throw new EntityAlreadyExistException(username);
        }
         
        adminUser.setUsername(username);
        adminUser.setPassword(password);
        
        return this.adminRepository.save(adminUser);
    }

    public boolean isExistUsername(String username) {
        if(StringUtils.isBlank(username)) {
            throw new StringBlankException("username");
        }

        String usernameInDB = this.adminRepository.getUsername(username.trim());
        return StringUtils.isNotBlank(usernameInDB);
    }

    public AdminUser getUser(User user) {
        this.userService.checkValidUser(user);
        String username = user.getValidUsername();
        String password = user.getEncodedPassword();

        AdminUser userInDB = this.adminRepository.findByAccount(username, password);
        if (Objects.isNull(userInDB)) {
            throw new InvalidAccountException(INVALID_ACCOUNT);
        }

        return userInDB;
    }

    public void checkLoginSucceed(AdminUser userLogin, AdminUser userInDB) {
        this.checkValidUser(userLogin);
        this.checkValidUser(userInDB);

        String usernameLogin = userLogin.getValidUsername();
        String passwordLogin = userLogin.getEncodedPassword();

        String usernameInDB = userInDB.getValidUsername();
        String passwordInDB = userInDB.getValidPassword();

        if (!usernameLogin.equalsIgnoreCase(usernameInDB) || !passwordLogin.equals(passwordInDB)) {
            throw new InvalidAccountException(INVALID_ACCOUNT);
        }
    }

    public void checkValidUser(AdminUser user) {
        if (user == null) {
            throw new InvalidAccountException(INVALID_ACCOUNT);
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new InvalidAccountException(INVALID_ACCOUNT);
        }
    }

}
