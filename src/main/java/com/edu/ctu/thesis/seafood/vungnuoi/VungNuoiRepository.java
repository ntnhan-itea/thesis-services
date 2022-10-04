package com.edu.ctu.thesis.seafood.vungnuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VungNuoiRepository extends JpaRepository<VungNuoi, Long> {

    VungNuoi findByTenVungNuoi(String tenVungNuoi);

    @Query("FROM VungNuoi vn where vn.id = :id AND vn.user.username = :username AND vn.user.password = :password")
    VungNuoi findByIdAndUser(@Param("id") Long vungNuoiId,
            @Param("username") String username,
            @Param("password") String password);

}
