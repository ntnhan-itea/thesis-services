package com.edu.ctu.thesis.seafood.aonuoi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.point.Point;
import com.edu.ctu.thesis.seafood.point.PointService;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoiService;

@Service
public class AoNuoiService {

    @Autowired
    AoNuoiRepository aoNuoiRepository;

    @Autowired
    UserService userService;

    @Autowired
    VungNuoiService vungNuoiService;

    @Autowired
    PointService pointService;

    public AoNuoi createAoNuoi(Long vungNuoiId, AoNuoi aoNuoi) {
        User user = aoNuoi.getUser();
        VungNuoi vungNuoiInDB = this.vungNuoiService.findByIdAndUser(vungNuoiId, user);
        aoNuoi.setVungNuoi(vungNuoiInDB);
        aoNuoi.setUser(vungNuoiInDB.getUser());

        List<Point> points = aoNuoi.getListOfPoint();
        if (!CollectionUtils.isEmpty(points)) {
            points.stream().forEach(e -> e.setAoNuoi(aoNuoi));
        }

        return this.aoNuoiRepository.save(aoNuoi);
    }

    public AoNuoi updateAoNuoi(AoNuoi aoNuoi) {
        AoNuoi aoNuoiInDB = this.findById(aoNuoi.getId());
        this.userService.checkLoginSucceed(aoNuoi.getUser(), aoNuoiInDB.getUser());
        this.createNewPointsWhenUpdate(aoNuoi, aoNuoiInDB);

        aoNuoiInDB.copy(aoNuoi);
        return this.aoNuoiRepository.save(aoNuoiInDB);
    }

    private void createNewPointsWhenUpdate(AoNuoi aoNuoi, AoNuoi aoNuoiInDB) {
        List<Point> points = aoNuoi.getListOfPoint();
        if (!CollectionUtils.isEmpty(points)) {
            this.pointService.deleteAllPointByAoNuoiId(aoNuoi.getId());

            points.stream().forEach(e -> e.setAoNuoi(aoNuoiInDB));
            this.pointService.createPoint(points);
        }
    }

    public AoNuoi findById(Long id) {
        Optional<AoNuoi> aoNuoi = this.aoNuoiRepository.findById(id);
        if (!aoNuoi.isPresent()) {
            throw new IllegalArgumentException("Cannot find Ao Nuoi [" + id + "] in DB");
        }
        return aoNuoi.get();
    }

    public void removeAoNuoi(Long id, User user) {
        AoNuoi aoNuoiInDB = this.findById(id);
        this.userService.checkLoginSucceed(user, aoNuoiInDB.getUser());
        this.aoNuoiRepository.delete(aoNuoiInDB);
    }

    public AoNuoi findByIdAndUser(Long id, User user) {
        AoNuoi aoNuoi = this.findById(id);
        this.userService.checkLoginSucceed(user, aoNuoi.getUser());
        return aoNuoi;
    }

}
