package com.fmss.cokdahakolayik.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "address_line", nullable = false)
    private String addressLine;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private Integer postcode;

    @OneToOne(mappedBy = "contactInformation", fetch = FetchType.LAZY)
    private Employee employee;
}
