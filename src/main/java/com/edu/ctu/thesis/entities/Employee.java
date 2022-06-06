package com.edu.ctu.thesis.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Data
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "first_name", length = 50, nullable = false, unique = true)
    private String firstName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
}
