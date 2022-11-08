package com.edu.ctu.thesis.seafood.congviec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.quytrinh.QuyTrinh;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cong_viec")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CongViec extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "ngay_thuc_hien")
    private Long ngayThucHien;

    @Column(name = "ten_cong_viec")
    private String tenCongViec;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "trang_thai")
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "quy_trinh_id", nullable = false)
    @JsonIgnore
    private QuyTrinh quyTrinh;

    @Valid
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(CongViec congViec) {
        this.ngayThucHien = congViec.ngayThucHien;
        this.tenCongViec = congViec.tenCongViec;
        this.noiDung = congViec.noiDung;
        this.trangThai = congViec.trangThai;
    }

}
