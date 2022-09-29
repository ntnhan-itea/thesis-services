package com.edu.ctu.thesis.seafood.TraiNuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraiNuoiRepository extends JpaRepository<TraiNuoi, Long> {
    
    @Query(value= "FROM TraiNuoi tn WHERE tn.user.username = :username and tn.user.password = :password")
    TraiNuoi findByAccount(@Param("username") String username, @Param("password") String password);

    // @Query(value= "FROM TraiNuoi tn WHERE tn.user.username = :username and tn.user.password = :password")
    // TraiNuoi findByUsername(String username);

}
