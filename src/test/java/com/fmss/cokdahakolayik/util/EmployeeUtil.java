package com.fmss.cokdahakolayik.util;

import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.response.EmployeeDto;
import com.fmss.cokdahakolayik.model.entity.ContactInformation;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.model.entity.PersonalInformation;
import com.fmss.cokdahakolayik.model.entity.WorkInformation;
import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;

import java.time.LocalDate;

public class EmployeeUtil extends BaseUtil{

    // Employee ####################################################################################################
    public static CreateEmployeeRequest generateCreateEmployeeRequest() {
        return CreateEmployeeRequest.builder()
                .email("oguzcan.bicer@fmsstech.com")
                .firstname("oguzcan")
                .lastname("bicer")
                .birthdate(LocalDate.of(1996, 1, 9))
                .phoneNumber("055555555")
                .addressLine("Üniversite mah, Avcılar")
                .city("Avcılar")
                .country("Turkey")
                .postcode(34320)
                .salary(11111.1)
                .department(Department.BACKEND)
                .startWorkDate(LocalDate.of(2022, 10, 1))
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerTitle(DeveloperTitle.JAVA)
                .developerTier(DeveloperTier.J1)
                .build();
    }

    public static UpdateEmployeeRequest generateUpdateEmployeeRequest() {
        return UpdateEmployeeRequest.builder()
                .email("oguzcanbicer96@gmail.com")
                .firstname("oguzcanUpdated")
                .lastname("bicer")
                .birthdate(LocalDate.of(1996, 1, 9))
                .phoneNumber("022222222")
                .addressLine("Üniversite mah, Avcılar")
                .city("Avcılar")
                .country("Turkey")
                .postcode(34320)
                .salary(22222.2)
                .department(Department.FULLSTACK)
                .startWorkDate(LocalDate.of(2022, 10, 1))
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerTitle(DeveloperTitle.JAVA)
                .developerTier(DeveloperTier.J2)
                .build();
    }

    public static Employee generateEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail("oguzcan.bicer@fmsstech.com");

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(id);
        personalInformation.setFirstname("oguzcan");
        personalInformation.setLastname("bicer");
        personalInformation.setBirthdate(LocalDate.of(1996, 1, 9));
        employee.setPersonalInformation(personalInformation);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setId(id);
        contactInformation.setPhoneNumber("055555555");
        contactInformation.setAddressLine("Üniversite mah, Avcılar");
        contactInformation.setCity("Avcılar");
        contactInformation.setCountry("Turkey");
        contactInformation.setPostcode(34320);
        employee.setContactInformation(contactInformation);

        WorkInformation workInformation = new WorkInformation();
        workInformation.setId(id);
        workInformation.setSalary(11111.1);
        workInformation.setDepartment(Department.BACKEND);
        workInformation.setStartWorkDate(LocalDate.of(2022, 10, 1));
        workInformation.setDeveloperLevel(DeveloperLevel.JUNIOR);
        workInformation.setDeveloperTitle(DeveloperTitle.JAVA);
        workInformation.setDeveloperTier(DeveloperTier.J1);
        employee.setWorkInformation(workInformation);
        return employee;
    }

    public static Employee generateEmployee2(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail("oguzcanbicer96@gmail.com");

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(id);
        personalInformation.setFirstname("oguzcanUpdated");
        personalInformation.setLastname("bicer");
        personalInformation.setBirthdate(LocalDate.of(1996, 1, 9));
        employee.setPersonalInformation(personalInformation);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setId(id);
        contactInformation.setPhoneNumber("022222222");
        contactInformation.setAddressLine("Üniversite mah, Avcılar");
        contactInformation.setCity("Avcılar");
        contactInformation.setCountry("Turkey");
        contactInformation.setPostcode(34320);
        employee.setContactInformation(contactInformation);

        WorkInformation workInformation = new WorkInformation();
        workInformation.setId(id);
        workInformation.setSalary(22222.2);
        workInformation.setDepartment(Department.FULLSTACK);
        workInformation.setStartWorkDate(LocalDate.of(2022, 10, 1));
        workInformation.setDeveloperLevel(DeveloperLevel.JUNIOR);
        workInformation.setDeveloperTitle(DeveloperTitle.JAVA);
        workInformation.setDeveloperTier(DeveloperTier.J2);
        employee.setWorkInformation(workInformation);
        return employee;
    }

    public static EmployeeDto generateEmployeeDto() {
        return EmployeeDto.builder()
                .email("oguzcan.bicer@fmsstech.com")
                .firstname("oguzcan")
                .lastname("bicer")
                .birthdate(LocalDate.of(1996, 1, 9))
                .phoneNumber("055555555")
                .addressLine("Üniversite mah, Avcılar")
                .city("Avcılar")
                .country("Turkey")
                .postcode(34320)
                .salary(11111.1)
                .department(Department.BACKEND)
                .startWorkDate(LocalDate.of(2022, 10, 1))
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerTitle(DeveloperTitle.JAVA)
                .developerTier(DeveloperTier.J1)
                .build();
    }

    public static EmployeeDto generateEmployeeDto2() {
        return EmployeeDto.builder()
                .email("oguzcanbicer96@gmail.com")
                .firstname("oguzcanUpdated")
                .lastname("bicer")
                .birthdate(LocalDate.of(1996, 1, 9))
                .phoneNumber("022222222")
                .addressLine("Üniversite mah, Avcılar")
                .city("Avcılar")
                .country("Turkey")
                .postcode(34320)
                .salary(22222.2)
                .department(Department.FULLSTACK)
                .startWorkDate(LocalDate.of(2022, 10, 1))
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerTitle(DeveloperTitle.JAVA)
                .developerTier(DeveloperTier.J2)
                .build();
    }
}
