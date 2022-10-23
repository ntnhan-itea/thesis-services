package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuanBiAoNuoiRepository extends JpaRepository<ChuanBiAoNuoi, Long> {
    
    @Query(value = "FROM ChuanBiAoNuoi cb where cb.nhatKy.id = :nhatKyId")
    ChuanBiAoNuoi findByNhatKyId(@Param("nhatKyId") Long nhatKyId);
}
