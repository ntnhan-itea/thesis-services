package com.edu.ctu.thesis.seafood.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.edu.ctu.thesis.audit.Audit;
import com.edu.ctu.thesis.audit.AuditListener;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Log4j2
@EntityListeners(AuditListener.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    @NotBlank(message = "Username should not be blank")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be blank")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TraiNuoi traiNuoi;

    @Embedded
    // @JsonIgnore
    private Audit audit;

    // @PrePersist
    // public void logNewUserAttempt() {
    //     if(this.audit == null) {
    //         this.audit = new Audit();
    //     }
    //     LocalDateTime now = LocalDateTime.now();
    //     this.audit.setCreationUser(this.username);
    //     this.audit.setCreationTime(now);
 
    //     this.audit.setModificationUser(this.username);
    //     this.audit.setModificationTime(now);

    //     log.info("Creation user [{}] - Creation time [{}].", this.audit.getCreationUser(), this.audit.getCreationTime());
    // }


    // @PreUpdate
    // public void logUserUpdateAttempt() {
    //     if(this.audit == null) {
    //         this.audit = new Audit();
    //     }

    //     LocalDateTime now = LocalDateTime.now();
    //     this.audit.setModificationUser(this.username);
    //     this.audit.setModificationTime(now);

    //     log.info("Modification user [{}] - Modification time [{}].", this.username, now);
    // }




}
