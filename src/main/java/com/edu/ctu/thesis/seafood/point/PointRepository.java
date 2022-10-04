package com.edu.ctu.thesis.seafood.point;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("FROM Point p where p.vungNuoi.id = :vungNuoiId")
    List<Point> findByVungNuoiId(@Param("vungNuoiId") Long id);

    @Query("FROM Point p where p.aoNuoi.id = :aoNuoiId")
    List<Point> findByAoNuoiId(@Param("aoNuoiId") Long id);
}
