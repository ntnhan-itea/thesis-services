package com.edu.ctu.thesis.seafood.TraiNuoi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edu.ctu.thesis.seafood.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trai_nuoi")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraiNuoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_trai_nuoi", nullable = false)
    private String tenTraiNuoi;

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Column(name = "dien_thoai")
    private String dienThoai;

    @Column(name = "hinh_thuc_nuoi")
    private String hinhThucNuoi;

    @Column(name = "doi_tuoi_nuoi")
    private String doiTuoiNuoi;

    @Column(name = "dien_tich_nuoi")
    private float dienTichNuoi;

    @OneToOne
    @JsonIgnore
    private User user;

}
