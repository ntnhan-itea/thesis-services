package com.edu.ctu.thesis.seafood.user;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.edu.ctu.thesis.audit.Audit;
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
@EqualsAndHashCode(of = {"username"})
@Validated
// @EntityListeners(AuditListener.class)
public class User {

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
    // @Size(min = 3, max = 30, message = "The password must not be less than {min} and more than {max}")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private String newPassword;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "trai_nuoi_id", nullable = false)
    private TraiNuoi traiNuoi;

    // @JsonProperty(access = Access.READ_ONLY)
    // @Embedded
    // private Audit audit;

    public void copy(User user) {
        this.password = ThesisUtils.encodeBase64(user.newPassword.trim());
        if(StringUtils.isNotBlank(user.fullName)) {
            this.fullName = user.fullName.trim();
        }
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
