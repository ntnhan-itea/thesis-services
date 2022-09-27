package com.edu.ctu.thesis.seafood.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    
    public User getUser(User user) throws IllegalAccessException {
        if(user == null) {
            throw new IllegalAccessException("Username or password invalid");
        }
        return this.userRepository.findByAccount(user.getUsername(), user.getPassword());
    }




}
