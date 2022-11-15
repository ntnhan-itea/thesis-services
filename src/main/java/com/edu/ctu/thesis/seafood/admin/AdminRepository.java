package com.edu.ctu.thesis.seafood.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {

    @Query(value = "SELECT ad.username FROM AdminUser ad WHERE ad.username = :username")
    String getUsername(@Param("username") String username);
}
