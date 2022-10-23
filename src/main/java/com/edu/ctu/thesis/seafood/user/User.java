package com.edu.ctu.thesis.seafood.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.audit.AuditInterface;
import com.edu.ctu.thesis.audit.AuditListener;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.util.ThesisUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = { "username" })
@Validated
@EntityListeners(AuditListener.class)
public class User implements AuditInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    @NotBlank(message = "Username should not be blank")
    @Size(min = 3, max = 30, message = "The username must not be less than {min} and more than {max}")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be blank")
    // @Size(min = 3, max = 30, message = "The password must not be less than {min}
    // and more than {max}")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private String newPassword;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TraiNuoi traiNuoi;

    @JsonIgnore
    @Embedded
    private Audit audit;

    public void copy(User user) {
        if (StringUtils.isNotBlank(user.fullName)) {
            this.fullName = user.fullName.trim();
        }

        if (StringUtils.isNotBlank(user.newPassword)) {
            this.password = ThesisUtils.encodeBase64(user.newPassword.trim());
        }

        if (user.gender != null) {
            this.gender = user.gender;
        }
    }

    @JsonIgnore
    public String getValidUsername() {
        return StringUtils.isBlank(this.username) ? null : this.username.trim().toLowerCase();
    }

    @JsonIgnore
    public String getValidPassword() {
        return StringUtils.isBlank(this.password) ? null : this.password.trim();
    }

    @JsonIgnore
    public String getEncodedPassword() {
        return StringUtils.isBlank(this.password) ? null : ThesisUtils.encodeBase64(this.password.trim());
    }

    public void clear() {
        this.username = null;
        this.password = null;
        this.newPassword = null;
        this.fullName = null;
        this.traiNuoi = null;
        this.audit = null;
    }

    @Override
    public void setCreationUser(String creationUser) {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        this.audit.setCreationUser(this.username);
    }

    @Override
    public void setCreationTime(LocalDateTime creationTime) {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        this.audit.setCreationTime(creationTime);
    }

    @Override
    public void setModificationUser(String modificationUser) {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        this.audit.setModificationUser(this.username);
    }

    @Override
    public void setModificationTime(LocalDateTime modificationTime) {
        if (this.audit == null) {
            this.audit = new Audit();
        }
        this.audit.setModificationTime(modificationTime);
    }

    // @PrePersist
    // public void logNewUserAttempt() {
    // if(this.audit == null) {
    // this.audit = new Audit();
    // }
    // LocalDateTime now = LocalDateTime.now();
    // this.audit.setCreationUser(this.username);
    // this.audit.setCreationTime(now);

    // this.audit.setModificationUser(this.username);
    // this.audit.setModificationTime(now);

    // log.info("Creation user [{}] - Creation time [{}].",
    // this.audit.getCreationUser(), this.audit.getCreationTime());
    // }

    // @PreUpdate
    // public void logUserUpdateAttempt() {
    // if(this.audit == null) {
    // this.audit = new Audit();
    // }

    // LocalDateTime now = LocalDateTime.now();
    // this.audit.setModificationUser(this.username);
    // this.audit.setModificationTime(now);

    // log.info("Modification user [{}] - Modification time [{}].", this.username,
    // now);
    // }

}
