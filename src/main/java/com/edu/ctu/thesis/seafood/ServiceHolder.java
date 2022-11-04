package com.edu.ctu.thesis.seafood;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoiService;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoiService;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoi;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoiService;
import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoach;
import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoachService;
import com.edu.ctu.thesis.seafood.lantheodoitangtruong.LanTheoDoiTangTruong;
import com.edu.ctu.thesis.seafood.lantheodoitangtruong.LanTheoDoiTangTruongService;
import com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi.MauChatLuongNuocAoNuoi;
import com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi.MauChatLuongNuocAoNuoiService;
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.nhatky.NhatKyService;
import com.edu.ctu.thesis.seafood.thagiong.ThaGiong;
import com.edu.ctu.thesis.seafood.thagiong.ThaGiongService;
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTao;
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTaoService;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.user.UserService;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoi;
import com.edu.ctu.thesis.seafood.vungnuoi.VungNuoiService;

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

    @Autowired
    ThaGiongService thaGiongService;

    @Autowired
    UserService userService;

    @Autowired
    VungNuoiService vungNuoiService;

    @Autowired
    TraiNuoiService traiNuoiService;

    @Autowired
    KetQuaThuHoachService ketQuaThuHoachService;

    @Autowired
    LanTheoDoiTangTruongService lanTheoDoiTangTruongService;

    @Autowired
    MauChatLuongNuocAoNuoiService mauChatLuongNuocAoNuoiService;

    public TraiNuoi createTraiNuoi(TraiNuoi traiNuoi) {
        return this.userService.createUser(traiNuoi).getTraiNuoi();
    }

    public VungNuoi createVungNuoi(VungNuoi vungNuoi) {
        User user = vungNuoi.getUser();
        TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
        User userInDB = traiNuoiInDB.getUser();

        vungNuoi.setTraiNuoi(traiNuoiInDB);
        vungNuoi.setUser(userInDB);
        return this.vungNuoiService.createVungNuoi(vungNuoi);
    }

    public AoNuoi createAoNuoi(Long vungNuoiId, AoNuoi aoNuoi) {
        User user = aoNuoi.getUser();
        VungNuoi vungNuoiInDB = this.vungNuoiService.findByIdAndUser(vungNuoiId, user);
        aoNuoi.setVungNuoi(vungNuoiInDB);
        aoNuoi.setUser(vungNuoiInDB.getUser());
        return this.aoNuoiService.createAoNuoi(aoNuoi);
    }

    public NhatKy createNhatKy(Long aoNuoiId, NhatKy nhatKy) {
        if (nhatKy == null) {
            throw new IllegalArgumentException("Invalid Nhat Ky!");
        }

        AoNuoi aoNuoiInDB = this.aoNuoiService.findByIdAndUser(aoNuoiId, nhatKy.getUser());
        User userInDB = aoNuoiInDB.getUser();

        nhatKy.setAoNuoi(aoNuoiInDB);
        nhatKy.setUser(userInDB);

        ChuanBiAoNuoi chuanBiAoNuoi = new ChuanBiAoNuoi();
        chuanBiAoNuoi.setNhatKy(nhatKy);
        chuanBiAoNuoi.setUser(userInDB);
        nhatKy.setChuanBiAoNuoi(chuanBiAoNuoi);

        ThaGiong thaGiong = new ThaGiong();
        thaGiong.setNhatKy(nhatKy);
        thaGiong.setUser(userInDB);
        nhatKy.setThaGiong(thaGiong);

        KetQuaThuHoach ketQuaThuHoachk = new KetQuaThuHoach();
        ketQuaThuHoachk.setNhatKy(nhatKy);
        ketQuaThuHoachk.setUser(userInDB);
        nhatKy.setKetQuaThuHoach(ketQuaThuHoachk);

        return this.nhatKyService.create(nhatKy);
    }

    public ThanhPhanCaiTao createThanhPhanCaiTao(Long chuanBiAoNuoiId, ThanhPhanCaiTao thanhPhanCaiTao) {
        if (thanhPhanCaiTao == null) {
            throw new IllegalArgumentException("Invalid Thanh Phan Cai Tao!");
        }

        ChuanBiAoNuoi chuanBiAoNuoiInDB = this.chuanBiAoNuoiService.findById(chuanBiAoNuoiId,
                thanhPhanCaiTao.getUser());

        thanhPhanCaiTao.setChuanBiAoNuoi(chuanBiAoNuoiInDB);
        thanhPhanCaiTao.setUser(chuanBiAoNuoiInDB.getUser());

        return this.thanhPhanCaiTaoService.create(thanhPhanCaiTao);
    }

    public ChuanBiAoNuoi createChuanBiAoNuoi(Long nhatKyId, ChuanBiAoNuoi chuanBiAoNuoi) {
        if (chuanBiAoNuoi == null) {
            throw new IllegalArgumentException("Invalid Chuan Bi Ao Nuoi!");
        }

        NhatKy nhatKyInDB = this.nhatKyService.findById(nhatKyId, chuanBiAoNuoi.getUser());
        User userInDB = nhatKyInDB.getUser();

        chuanBiAoNuoi.setNhatKy(nhatKyInDB);
        chuanBiAoNuoi.setUser(userInDB);

        List<ThanhPhanCaiTao> thanhPhanCaiTaos = chuanBiAoNuoi.getThanhPhanCaiTaos();
        if (!CollectionUtils.isEmpty(thanhPhanCaiTaos)) {
            thanhPhanCaiTaos.stream().filter(Objects::nonNull).forEach(e -> {
                e.setChuanBiAoNuoi(chuanBiAoNuoi);
                e.setUser(userInDB);
            });
        }

        return this.chuanBiAoNuoiService.create(chuanBiAoNuoi);
    }

    public NhatKy getLastestNhatKyOfAoNuoi(Long nhatKyId, User user) {
        AoNuoi aoNuoiInDB = this.aoNuoiService.findByIdAndUser(nhatKyId, user);
        List<NhatKy> nhatKies = aoNuoiInDB.getListOfNhatKy();
        NhatKy nhatKy = nhatKies.stream().max(Comparator.comparing(NhatKy::getCreationTime)).get();
        return nhatKy;
    }

    public ThaGiong createThaGiong(Long nhatKyId, ThaGiong thaGiong) {
        if (thaGiong == null) {
            throw new IllegalArgumentException("Invalid Tha Giong input!");
        }

        NhatKy nhatKyInDB = this.nhatKyService.findById(nhatKyId, thaGiong.getUser());
        User userInDB = nhatKyInDB.getUser();

        thaGiong.setNhatKy(nhatKyInDB);
        thaGiong.setUser(userInDB);

        return this.thaGiongService.create(thaGiong);
    }

    public KetQuaThuHoach createKetQuaThuHoach(Long nhatKyId, KetQuaThuHoach ketQuaThuHoach) {
        NhatKy nhatKyInDB = this.nhatKyService.findById(nhatKyId, ketQuaThuHoach.getUser());
        User userInDB = nhatKyInDB.getUser();

        ketQuaThuHoach.setNhatKy(nhatKyInDB);
        ketQuaThuHoach.setUser(userInDB);

        return this.ketQuaThuHoachService.create(ketQuaThuHoach);
    }

    public LanTheoDoiTangTruong createLanTheoDoiTangTruong(Long nhatKyId, LanTheoDoiTangTruong lanTheoDoiTangTruong) {
        NhatKy nhatKyInDB = this.nhatKyService.findById(nhatKyId, lanTheoDoiTangTruong.getUser());
        User userInDB = nhatKyInDB.getUser();

        lanTheoDoiTangTruong.setNhatKy(nhatKyInDB);
        lanTheoDoiTangTruong.setUser(userInDB);

        return this.lanTheoDoiTangTruongService.create(lanTheoDoiTangTruong);
    }

    public MauChatLuongNuocAoNuoi createMauChatLuongNuocAoNuoi(Long nhatKyId, MauChatLuongNuocAoNuoi mauChatLuongNuocAoNuoi) {
        NhatKy nhatKyInDB = this.nhatKyService.findById(nhatKyId, mauChatLuongNuocAoNuoi.getUser());
        User userInDB = nhatKyInDB.getUser();

        mauChatLuongNuocAoNuoi.setNhatKy(nhatKyInDB);
        mauChatLuongNuocAoNuoi.setUser(userInDB);

        return this.mauChatLuongNuocAoNuoiService.create(mauChatLuongNuocAoNuoi);
    }

}
