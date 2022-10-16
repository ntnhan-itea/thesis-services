package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuanBiAoNuoiRepository extends JpaRepository<ChuanBiAoNuoi, Long> {
    
}
