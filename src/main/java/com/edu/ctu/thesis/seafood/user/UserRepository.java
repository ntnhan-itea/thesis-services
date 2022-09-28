package com.edu.ctu.thesis.seafood.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value= "FROM User user WHERE user.username = :username and user.password = :password")
    User findByAccount(@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);
}
