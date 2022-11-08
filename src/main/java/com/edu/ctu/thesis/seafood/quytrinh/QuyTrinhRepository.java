package com.edu.ctu.thesis.seafood.quytrinh;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyTrinhRepository extends JpaRepository<QuyTrinh, Long> {
    
    @Query(value = "FROM QuyTrinh qt WHERE qt.nhatKy.id = :nhatKyId")
    Optional<QuyTrinh> existsByNhatKyId(@Param("nhatKyId") Long nhatKyId);
}
