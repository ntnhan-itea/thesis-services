package com.edu.ctu.thesis.seafood.thagiong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThaGiongRepository extends JpaRepository<ThaGiong, Long> {
    
    @Query(value = "FROM ThaGiong tg where tg.nhatKy.id = :nhatKyId")
    ThaGiong findByNhatKyId(@Param("nhatKyId") Long nhatKyId);
}
