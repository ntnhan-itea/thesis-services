package com.edu.ctu.thesis.employee;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
 
    @Column(name = "first_name", length = 50)
    @NotBlank(message = "First name should not be missing!")
    private String firstName;

    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @Column(name = "account", nullable = false, unique = true)
    @Size(max = 30, message = "The account should not be longer than 30 characters")
    private String account;

    @Column(name = "password", nullable = false)
    private String password;
}
