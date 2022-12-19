package com.fmss.cokdahakolayik.client.dto.response;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeDto(String email,
// Personal information:
                          String firstname,
                          String lastname,
                          LocalDate birthdate,
//Work information:
                          Double salary,
                          Department department,
                          LocalDate startWorkDate,
                          DeveloperLevel developerLevel,
                          DeveloperTier developerTier,
                          DeveloperTitle developerTitle,
//Contact information:
                          String phoneNumber,
                          String addressLine,
                          String city,
                          String country,
                          Integer postcode
) {
}
