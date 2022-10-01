package com.edu.ctu.thesis.seafood.vungnuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VungNuoiRepository extends JpaRepository<VungNuoi, Long> {
    
    VungNuoi findByTenVungNuoi(String tenVungNuoi);
}
