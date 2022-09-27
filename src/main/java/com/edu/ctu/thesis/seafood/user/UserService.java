package com.edu.ctu.thesis.seafood.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        TraiNuoi traiNuoi = user.getTraiNuoi();
        traiNuoi.setUser(user);
        return this.userRepository.save(user);
    }

    
    public User getUser(User user) throws IllegalAccessException {
        if(user == null) {
            throw new IllegalAccessException("Username or password invalid");
        }
        return this.userRepository.findByAccount(user.getUsername(), user.getPassword());
    }




}
