package com.edu.ctu.thesis.seafood.congviec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongViecRepository extends JpaRepository<CongViec, Long> {
    
}
