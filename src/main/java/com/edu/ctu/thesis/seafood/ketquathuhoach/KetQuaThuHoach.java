package com.edu.ctu.thesis.seafood.ketquathuhoach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.thuhoachvadoanhthu.ThuHoachVaDoanhThu;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ket_qua_thu_hoach")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KetQuaThuHoach extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "dien_tich_ao")
    private Float dienTichAo;

    @Column(name = "luong_tha_giong")
    private Long luongThaGiong;

    @Column(name = "ngay_tha_giong")
    private LocalDate ngayThaGiong;

    @Column(name = "ngay_thu_hoach")
    private LocalDate ngayThuHoach;

    @Column(name = "tong_so_ngay_nuoi")
    private Long tongSoNgayNuoi;

    @Column(name = "luong_thuc_an")
    private Float luongThucAnToanBo;

    @Column(name = "so_luong_thu_hoach")
    private Float soLuongThuHoach;

    @Column(name = "sulfit")
    private Float sulfit;

    @Column(name = "chi_phi_thuc_an")
    private Float chiPhiThucAn;

    @Column(name = "chi_phi_giong")
    private Float chiPhiGiong;

    @Column(name = "chi_phi_cai_tao")
    private Float chiPhiCaiTao;

    @Column(name = "chi_phi_dau_nhot")
    private Float chiPhiDauNhot;

    @Column(name = "chi_phi_cong_nhan")
    private Float chiPhiCongNhan;

    @Column(name = "chi_phi_phat_sinh")
    private Float chiPhiPhatSinh;

    @Column(name = "chi_phi_khac")
    private Float chiPhiKhac;

    @OneToMany(mappedBy = "ketQuaThuHoach", cascade = CascadeType.ALL)
    private List<ThuHoachVaDoanhThu> thuHoachVaDoanhThus = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "nhat_ky_id", nullable = false, unique = true)
    @JsonIgnore
    private NhatKy nhatKy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(KetQuaThuHoach entity) {
        this.dienTichAo = entity.dienTichAo;
        this.luongThaGiong = entity.luongThaGiong;
        this.ngayThaGiong = entity.ngayThaGiong;
        this.ngayThuHoach = entity.ngayThuHoach;
        this.tongSoNgayNuoi = entity.tongSoNgayNuoi;
        this.luongThucAnToanBo = entity.luongThucAnToanBo;
        this.soLuongThuHoach = entity.soLuongThuHoach;
        this.sulfit = entity.sulfit;
        this.chiPhiThucAn = entity.chiPhiThucAn;
        this.chiPhiGiong = entity.chiPhiGiong;
        this.chiPhiCaiTao = entity.chiPhiCaiTao;
        this.chiPhiDauNhot = entity.chiPhiDauNhot;
        this.chiPhiCongNhan = entity.chiPhiCongNhan;
        this.chiPhiPhatSinh = entity.chiPhiPhatSinh;
        this.chiPhiKhac = entity.chiPhiKhac;
    }

}
