package com.edu.ctu.thesis.seafood.nhatky;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.chuanbiaonuoi.ChuanBiAoNuoi;
import com.edu.ctu.thesis.seafood.ketquathuhoach.KetQuaThuHoach;
import com.edu.ctu.thesis.seafood.lantheodoitangtruong.LanTheoDoiTangTruong;
import com.edu.ctu.thesis.seafood.lanthucanthuoc.LanThucAnThuoc;
import com.edu.ctu.thesis.seafood.mauchatluongnuocaonuoi.MauChatLuongNuocAoNuoi;
import com.edu.ctu.thesis.seafood.quytrinh.QuyTrinh;
import com.edu.ctu.thesis.seafood.thagiong.ThaGiong;
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
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nhat_ky")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhatKy extends Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @Column(name = "vu_nuoi")
    private String vuNuoi;

    @Column(name = "nam_nuoi", nullable = false)
    @NotNull(message = "Nam nuoi tom khong duoc bo trong")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate namNuoi;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ao_nuoi_id", nullable = false)
    private AoNuoi aoNuoi;

    @OneToOne(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private KetQuaThuHoach ketQuaThuHoach;

    @OneToOne(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private ChuanBiAoNuoi chuanBiAoNuoi;

    @OneToOne(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private ThaGiong thaGiong;

    @OneToOne(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private QuyTrinh quyTrinh;

    @OneToMany(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private List<LanTheoDoiTangTruong> lanTheoDoiTangTruongs = new ArrayList<>();

    @OneToMany(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private List<MauChatLuongNuocAoNuoi> mauChatLuongNuocAoNuois = new ArrayList<>();

    @OneToMany(mappedBy = "nhatKy", cascade = CascadeType.ALL)
    private List<LanThucAnThuoc> lanThucAnThuocs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(message = "Account should not be null")
    private User user;

    @JsonIgnore
    @Embedded
    private Audit audit;

    @PrePersist
    private void logNewUserAttempt() {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();

        this.audit.setCreationTime(now);
        this.audit.setModificationTime(now);
    }

    @PreUpdate
    private void logUserUpdateAttempt() {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        LocalDateTime now = LocalDateTime.now();
        this.audit.setModificationTime(now);
    }

    public void copy(NhatKy nhatKy) {
        this.vuNuoi = nhatKy.vuNuoi;
        this.namNuoi = nhatKy.namNuoi;
    }

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getCreationTime() {
        return this.audit.getCreationTime();
    }
}
