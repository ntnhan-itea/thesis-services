package com.edu.ctu.thesis.seafood.thanhphancaitao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhPhanCaiTaoRepository extends JpaRepository<ThanhPhanCaiTao, Long> {
    
}
