package com.edu.ctu.thesis.seafood.thuhoachvadoanhthu;

import java.time.LocalDate;

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

import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoach;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
@Table(name = "thu_hoach_va_doanh_thu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ThuHoachVaDoanhThu extends Validity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "ngay_thu_hoach")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate ngayThuHoach;

    @Column(name = "kich_co_tom")
    private Float kichCoTom;

    @Column(name = "tong_trong_luong")
    private Float tongTrongLuong;

    @Column(name = "so_luong_tom_thu_hoach")
    private Integer soLuongTomThuHoach;

    @Column(name = "gia_ban")
    private Float giaBan;

    @ManyToOne
    @JoinColumn(name = "ket_qua_thu_hoach_id", nullable = false)
    @JsonIgnore
    private KetQuaThuHoach ketQuaThuHoach;

    @Valid
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    @JsonProperty(access = Access.WRITE_ONLY)
    private User user;

    public void copy(ThuHoachVaDoanhThu entity) {
        this.ngayThuHoach = entity.ngayThuHoach;
        this.kichCoTom = entity.kichCoTom;
        this.tongTrongLuong = entity.tongTrongLuong;
        this.soLuongTomThuHoach = entity.soLuongTomThuHoach;
        this.giaBan = entity.giaBan;
    }

    

}
