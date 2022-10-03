package com.edu.ctu.thesis.seafood.vungnuoi;

import java.util.List;
import java.util.Optional;

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
        vungNuoi.setTraiNuoi(traiNuoiInDB);

        List<Point> points = vungNuoi.getListOfPoint();
        if (!CollectionUtils.isEmpty(points)) {
            points.stream().forEach(e -> e.setVungNuoi(vungNuoi));
        }

        return this.vungNuoiRepository.save(vungNuoi);
    }

    public VungNuoi updateVungNuoi(VungNuoi vungNuoi) {
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
                .orElseThrow(() -> new IllegalArgumentException("Cannot find vung nuoi [" + id + "] in your DB"));

        vungNuoiInDB.getTraiNuoi().setVungNuois(null);
        this.vungNuoiRepository.delete(vungNuoiInDB);
    }

}
