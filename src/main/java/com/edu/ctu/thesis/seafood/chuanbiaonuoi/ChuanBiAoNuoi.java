package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.thanhphancaitao.ThanhPhanCaiTao;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chuan_bi_ao_nuoi")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ChuanBiAoNuoi extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_bat_dau_cai_tao")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-mm-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ngayBatDauCaiTao;

    @Column(name = "do_ph_cua_dat")
    private Float doPhCuaDat;

    @Column(name = "cong_suat_may_quat_nuoc")
    private Float congSuatMayQuatNuoc;

    @Column(name = "hinh_thuc_cai_tao")
    private String hinhThucCaiTao;

    @Column(name = "so_canh_quat_tren_may")
    private Integer soCanhQuatTrenMay;

    @Column(name = "nen_day")
    @Convert(converter = NenDayConverter.class)
    private NenDay nenDay;

    @OneToOne
    @JoinColumn(name = "nhat_ky_id", nullable = false)
    @JsonIgnore
    private NhatKy nhatKy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuanBiAoNuoi")
    private List<ThanhPhanCaiTao> thanhPhanCaiTaos;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

}
