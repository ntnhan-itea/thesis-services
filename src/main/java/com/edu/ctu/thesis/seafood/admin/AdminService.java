package com.edu.ctu.thesis.seafood.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.exceptions.EntityAlreadyExistException;
import com.edu.ctu.thesis.exceptions.StringBlankException;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;
    
    public AdminUser create(AdminUser adminUser) {
        if(this.isExistUsername(adminUser.getUsername())) {
            throw new EntityAlreadyExistException(adminUser.getUsername());
        }

        return this.adminRepository.save(adminUser);
    }

    public boolean isExistUsername(String username) {
        if(StringUtils.isBlank(username)) {
            throw new StringBlankException("username");
        }

        String usernameInDB = this.adminRepository.getUsername(username.trim());
        return StringUtils.isNotBlank(usernameInDB);
    }

}
