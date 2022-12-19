package com.fmss.cokdahakolayik.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    private LocalDate birthdate;

    @OneToOne(mappedBy = "personalInformation")
    private Employee employee;
}
