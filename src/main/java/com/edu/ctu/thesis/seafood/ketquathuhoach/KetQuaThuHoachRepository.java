package com.edu.ctu.thesis.seafood.ketquathuhoach;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KetQuaThuHoachRepository extends JpaRepository<KetQuaThuHoach, Long> {

    @Query(value = "FROM KetQuaThuHoach kq where kq.nhatKy.id = :nhatKyId")
    Optional<KetQuaThuHoach> findByNhatKyId(@Param("nhatKyId") Long nhatKyId);

}
