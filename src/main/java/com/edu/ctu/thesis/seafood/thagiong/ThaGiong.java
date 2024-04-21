package com.edu.ctu.thesis.seafood.thagiong;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.edu.ctu.thesis.seafood.dieukienmoitruong.DieuKienMoiTruong;
import com.edu.ctu.thesis.seafood.nhatky.NhatKy;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tha_giong")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public class ThaGiong extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "nguon_goc_giong")
    private String nguonGocGiong;

    @Column(name = "gia_giong")
    private Float giaGiong;

    @Column(name = "so_lo_giong")
    private Integer soLoGiong;

    @Column(name = "da_kiem_tra_PCR")
    private Boolean tomQuaKiemTraPCR;

    @Column(name = "ket_qua_kiem")
    private String ketQuaKiem;

    @Column(name = "co_tom_giong_PL")
    private Float coTomGiongPL;

    @Column(name = "so_luong_tha_giong")
    private Integer soLuongThaGiong;

    @Column(name = "mat_do")
    private String matDo;

    @Column(name = "dien_giai")
    private String dienDai;

    @Column(name = "xy_ly_con_giong_truoc_khi_nuoi")
    private Boolean xuLyConGiongTruocKhiNuoi;

    @Column(name = "cach_xu_ly")
    private String cachXuLy;

    @Column(name = "ngay_tha_giong")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate ngayThaGiong;

    @Column(name = "chat_luong_giong")
    @Convert(converter = ChatLuongGiongConverter.class)
    private ChatLuongGiong chatLuongGiong;

    @Embedded
    DieuKienMoiTruong dieuKienMoiTruong;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "nhat_ky_id", nullable = false, unique = true)
    private NhatKy nhatKy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(message = "Account should not be null")
    private User user;

    public void copy(ThaGiong thaGiong) {
        this.nguonGocGiong = thaGiong.nguonGocGiong;
        this.giaGiong = thaGiong.giaGiong;
        this.soLoGiong = thaGiong.soLoGiong;
        this.tomQuaKiemTraPCR = thaGiong.tomQuaKiemTraPCR;
        this.ketQuaKiem = thaGiong.ketQuaKiem;
        this.coTomGiongPL = thaGiong.coTomGiongPL;
        this.soLuongThaGiong = thaGiong.soLuongThaGiong;
        this.matDo = thaGiong.matDo;
        this.dienDai = thaGiong.dienDai;
        this.xuLyConGiongTruocKhiNuoi = thaGiong.xuLyConGiongTruocKhiNuoi;
        this.cachXuLy = thaGiong.cachXuLy;
        this.ngayThaGiong = thaGiong.ngayThaGiong;
        this.chatLuongGiong = thaGiong.chatLuongGiong;
        this.updateDieuKienMoiTruong(thaGiong.dieuKienMoiTruong);        
    }

    private void updateDieuKienMoiTruong(DieuKienMoiTruong dieuKienMoiTruong) {
        if( this.dieuKienMoiTruong == null) {
            this.dieuKienMoiTruong = new DieuKienMoiTruong();
        }

        if(dieuKienMoiTruong == null) {
            this.dieuKienMoiTruong = null;
        } else {
            this.dieuKienMoiTruong.copy(dieuKienMoiTruong);
        }
    }

}
