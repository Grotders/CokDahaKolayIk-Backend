package com.fmss.cokdahakolayik.model.entity;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Entity
public class WorkInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double salary;

    @Enumerated(EnumType.STRING)
    private Department department;

    private LocalDate startWorkDate;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    @Enumerated(EnumType.STRING)
    private DeveloperTier developerTier;

    @Enumerated(EnumType.STRING)
    private DeveloperTitle developerTitle;

    @OneToOne(mappedBy = "workInformation")
    private Employee employee;
}
