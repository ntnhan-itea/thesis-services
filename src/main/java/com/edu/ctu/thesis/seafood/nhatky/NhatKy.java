package com.edu.ctu.thesis.seafood.nhatky;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.seafood.aonuoi.AoNuoi;
import com.edu.ctu.thesis.seafood.user.User;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
}