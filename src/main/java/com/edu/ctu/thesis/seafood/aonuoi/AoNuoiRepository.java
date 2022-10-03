package com.edu.ctu.thesis.seafood.aonuoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AoNuoiRepository extends JpaRepository<AoNuoi, Long> {
    
}
