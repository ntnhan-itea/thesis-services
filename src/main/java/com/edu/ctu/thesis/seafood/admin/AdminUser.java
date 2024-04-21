package com.edu.ctu.thesis.seafood.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.edu.ctu.thesis.util.ThesisUtils;
import com.edu.ctu.thesis.validation.phonenumber.PhoneNumberValidation;
import com.edu.ctu.thesis.validity.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_admin")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AdminUser extends Validity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    // @UniqueUsernameValidation
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    @NotBlank(message = "password should not be blank")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @PhoneNumberValidation
    @Column(name = "phone_number")
    private String phoneNumber;

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


}
