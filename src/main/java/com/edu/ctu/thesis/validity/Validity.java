package com.edu.ctu.thesis.validity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Validity {

    @Column(name = "valid_to")
    @JsonIgnore
    private LocalDate validTo;
}