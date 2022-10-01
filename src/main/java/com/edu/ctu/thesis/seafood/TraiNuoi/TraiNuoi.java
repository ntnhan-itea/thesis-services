package com.edu.ctu.thesis.seafood.TraiNuoi;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.audit.AuditListener;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.seafood.valididy.Validity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trai_nuoi")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditListener.class)
@EqualsAndHashCode(callSuper = false, of = { "user" })
public class TraiNuoi extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_trai_nuoi", nullable = false)
    @NotBlank(message = "Ten trai nuoi khong duoc phep bo trong")
    private String tenTraiNuoi;

    @Column(name = "dia_chi", nullable = false)
    @NotBlank(message = "Dia chi trai nuoi khong duoc phep bo trong")
    private String diaChi;

    @Column(name = "dien_thoai")
    private String dienThoai;

    @Column(name = "hinh_thuc_nuoi")
    private String hinhThucNuoi;

    @Column(name = "doi_tuoi_nuoi")
    private String doiTuoiNuoi;

    @Column(name = "dien_tich_nuoi")
    private Float dienTichNuoi;

    @OneToOne(mappedBy = "traiNuoi", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull(message = "User should not be null")
    private User user;

    // @JsonIgnore
    @JsonProperty(access = Access.READ_ONLY)
    @Embedded
    private Audit audit;

    public void copy(TraiNuoi traiNuoi) {
        this.tenTraiNuoi = traiNuoi.tenTraiNuoi;
        this.diaChi = traiNuoi.diaChi;
        this.dienThoai = traiNuoi.dienThoai;
        this.hinhThucNuoi = traiNuoi.hinhThucNuoi;
        this.doiTuoiNuoi = traiNuoi.doiTuoiNuoi;
        this.dienTichNuoi = traiNuoi.dienTichNuoi;
        this.user.setFullName(traiNuoi.user.getFullName());
    }

}
