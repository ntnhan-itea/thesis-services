package com.edu.ctu.thesis.seafood.vungnuoi;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoiService;
import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.point.PointService;
import com.edu.ctu.thesis.seafood.user.User;

@Service
public class VungNuoiService {

    @Autowired
    VungNuoiRepository vungNuoiRepository;

    @Autowired
    TraiNuoiService traiNuoiService;

    @Autowired
    PointService pointService;

    public VungNuoi createVungNuoi(VungNuoi vungNuoi) {
        User user = vungNuoi.getUser();
        TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
        String tenVungNuoi = this.checkVungNuoiIsNotExistInDB(vungNuoi);
        vungNuoi.setTraiNuoi(traiNuoiInDB);
        vungNuoi.setTenVungNuoi(tenVungNuoi);

        List<Point> points = vungNuoi.getListOfPoint();
        if (!CollectionUtils.isEmpty(points)) {
            points.stream().forEach(e -> e.setVungNuoi(vungNuoi));
        }

        return this.vungNuoiRepository.save(vungNuoi);
    }

    public VungNuoi updateVungNuoi(VungNuoi vungNuoi) {
        this.checkVungNuoiIsNotExistInDB(vungNuoi);
        VungNuoi vungNuoiInDB = this.findById(vungNuoi.getId());
        this.createNewPointsWhenUpdate(vungNuoi, vungNuoiInDB);

        vungNuoiInDB.copy(vungNuoi);
        return this.vungNuoiRepository.save(vungNuoiInDB);

    }

    private void createNewPointsWhenUpdate(VungNuoi vungNuoi, VungNuoi vungNuoiInDB) {
        List<Point> points = vungNuoi.getListOfPoint();
        if (!CollectionUtils.isEmpty(points)) {
            this.pointService.deleteAllPointByVungNuoiId(vungNuoi.getId());

            points.stream().forEach(e -> e.setVungNuoi(vungNuoiInDB));
            this.pointService.createPoint(points);
        }
    }

    public VungNuoi findById(Long id) {
        Optional<VungNuoi> vungNuoi = this.vungNuoiRepository.findById(id);
        if (!vungNuoi.isPresent()) {
            throw new IllegalArgumentException("Vung nuoi [" + id + "] khong tim thay trong DB!");
        }
        return vungNuoi.get();
    }

    public void removeVungNuoi(Long id, User user) {
        TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
        List<VungNuoi> vungNuoisInDB = traiNuoiInDB.getVungNuois();
        if (CollectionUtils.isEmpty(vungNuoisInDB)) {
            throw new IllegalArgumentException("Khong tim thay danh sach vung nuoi trong trai nuoi!");
        }
        VungNuoi vungNuoiInDB = this.findById(id);
        vungNuoisInDB.stream().filter(e -> vungNuoiInDB.getId().equals(e.getId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find vung nuoi [" + id + "] in DB"));

        vungNuoiInDB.getTraiNuoi().setVungNuois(null);
        this.vungNuoiRepository.delete(vungNuoiInDB);
    }

    private String checkVungNuoiIsNotExistInDB(VungNuoi vungNuoi) {
        if (vungNuoi == null) {
            throw new IllegalArgumentException("Invalid vung nuoi input!");
        }

        String tenVungNuoi = vungNuoi.getTenVungNuoi();
        VungNuoi vungNuoiInDB = this.getVungNuoiByTenVungNuoi(tenVungNuoi);
        if (vungNuoiInDB != null) {
            throw new IllegalArgumentException("Vung nuoi [" + tenVungNuoi.trim() + "] da ton tai trong DB!");
        }
        return tenVungNuoi.trim();
    }

    private VungNuoi getVungNuoiByTenVungNuoi(String tenVungNuoi) {
        if (StringUtils.isBlank(tenVungNuoi)) {
            throw new IllegalArgumentException("Invalid ten vung nuoi input!");
        }

        return this.vungNuoiRepository.findByTenVungNuoi(tenVungNuoi.trim());
    }

}
