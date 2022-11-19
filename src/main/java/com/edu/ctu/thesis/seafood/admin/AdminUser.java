package com.edu.ctu.thesis.seafood.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.edu.ctu.thesis.validation.phonenumber.PhoneNumberValidation;
import com.edu.ctu.thesis.validity.Validity;
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


}
