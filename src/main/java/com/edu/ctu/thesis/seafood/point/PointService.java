package com.edu.ctu.thesis.seafood.point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class PointService {

    @Autowired
    PointRepository pointRepository;

    public Point createPoint(Point point) {
        return this.pointRepository.save(point);
    }

    public List<Point> createPoint(List<Point> points) {
        return this.pointRepository.saveAll(points);
    }

    public void deleteByOnes(List<Point> points) {
        if (!CollectionUtils.isEmpty(points)) {
            this.pointRepository.deleteAll(points);
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id input!");
        }

        this.pointRepository.deleteAllById(Arrays.asList(id));
    }

    public List<Point> findAllById(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return this.pointRepository.findAllById(ids);
    }

    public List<Point> findByVungNuoiId(Long id) {
        return this.pointRepository.findByVungNuoiId(id);
    }

    public List<Point> findByAoNuoiId(Long id) {
        return this.pointRepository.findByAoNuoiId(id);
    }

    public void deleteAllPointByVungNuoiId(Long vungNuoiId) {
        List<Point> points = this.findByVungNuoiId(vungNuoiId);
        this.deleteByOnes(points);
    }

    public void deleteAllPointByAoNuoiId(Long aoNuoiId) {
        List<Point> points = this.findByAoNuoiId(aoNuoiId);
        this.deleteByOnes(points);
    }

}
