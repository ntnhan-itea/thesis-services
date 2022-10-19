package com.edu.ctu.thesis.seafood;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoiService;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoi;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoiService;
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.nhatky.NhatKyService;
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTao;
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTaoService;
import com.edu.ctu.thesis.seafood.user.User;

@Service
public class ServiceHolder {

    @Autowired
    NhatKyService nhatKyService;

    @Autowired
    AoNuoiService aoNuoiService;

    @Autowired
    ChuanBiAoNuoiService chuanBiAoNuoiService;

    @Autowired
    ThanhPhanCaiTaoService thanhPhanCaiTaoService;


    public NhatKy createNhatKy(Long aoNuoiId, NhatKy nhatKy) {
        if (nhatKy == null) {
            throw new IllegalArgumentException("Invalid Nhat Ky!");
        }

        AoNuoi aoNuoiInDB = this.aoNuoiService.findByIdAndUser(aoNuoiId, nhatKy.getUser());
        User userInDB = aoNuoiInDB.getUser();

        nhatKy.setAoNuoi(aoNuoiInDB);
        nhatKy.setUser(userInDB);

        ChuanBiAoNuoi chuanBiAoNuoi = nhatKy.getChuanBiAoNuoi();
        if (chuanBiAoNuoi == null) {
            chuanBiAoNuoi = new ChuanBiAoNuoi();
        }

        chuanBiAoNuoi.setNhatKy(nhatKy);
        chuanBiAoNuoi.setUser(userInDB);
        nhatKy.setChuanBiAoNuoi(chuanBiAoNuoi);

        return this.nhatKyService.create(nhatKy);
    }

    public ThanhPhanCaiTao createThanhPhanCaiTao(Long id, ThanhPhanCaiTao thanhPhanCaiTao) {
        if (thanhPhanCaiTao == null) {
            throw new IllegalArgumentException("Invalid Thanh Phan Cai Tao!");
        }

        ChuanBiAoNuoi chuanBiAoNuoiInDB = this.chuanBiAoNuoiService.findById(id, thanhPhanCaiTao.getUser());

        thanhPhanCaiTao.setId(null);
        thanhPhanCaiTao.setChuanBiAoNuoi(chuanBiAoNuoiInDB);
        thanhPhanCaiTao.setUser(chuanBiAoNuoiInDB.getUser());

        return this.thanhPhanCaiTaoService.create(thanhPhanCaiTao);
    }

    public ChuanBiAoNuoi createChuanBiAoNuoi(Long id, ChuanBiAoNuoi chuanBiAoNuoi) {
        if (chuanBiAoNuoi == null) {
            throw new IllegalArgumentException("Invalid Chuan Bi Ao Nuoi!");
        }

        NhatKy nhatKyInDB = this.nhatKyService.findById(id, chuanBiAoNuoi.getUser());
        User userInDB = nhatKyInDB.getUser();

        chuanBiAoNuoi.setId(null);
        chuanBiAoNuoi.setNhatKy(nhatKyInDB);
        chuanBiAoNuoi.setUser(userInDB);

        List<ThanhPhanCaiTao> thanhPhanCaiTaos = chuanBiAoNuoi.getThanhPhanCaiTaos();
        if (!CollectionUtils.isEmpty(thanhPhanCaiTaos)) {
            thanhPhanCaiTaos.stream().filter(Objects::nonNull).forEach(e -> {
                e.setChuanBiAoNuoi(chuanBiAoNuoi);
                e.setUser(userInDB);
            });
        }

        return this.chuanBiAoNuoiService.createChuanBiAoNuoi(chuanBiAoNuoi);
    }

    public NhatKy getLastestNhatKyOfAoNuoi(Long nhatKyId, User user) {
        AoNuoi aoNuoiInDB = this.aoNuoiService.findByIdAndUser(nhatKyId, user);
        List<NhatKy> nhatKies = aoNuoiInDB.getListOfNhatKy();
        NhatKy nhatKy = nhatKies.stream().max(Comparator.comparing(NhatKy::getLatestTimeAdded)).get();
        return nhatKy;
    }

}
