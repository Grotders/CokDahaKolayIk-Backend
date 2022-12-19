package com.fmss.cokdahakolayik.unit.employee;

import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.response.EmployeeDto;
import com.fmss.cokdahakolayik.mapper.EmployeeMapperImpl;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeConverterTest extends BaseUnitTest {


    @InjectMocks
    EmployeeMapperImpl employeeMapper;

    @Test
    void toEmployeeFromCreateEmployeeRequest() {
        CreateEmployeeRequest createEmployeeRequest = EmployeeUtil.generateCreateEmployeeRequest();

        Employee expected = EmployeeUtil.generateEmployee(null);
        Employee result = employeeMapper.toEmployeeFromCreateEmployeeRequest(createEmployeeRequest);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(expected.getEmail(), result.getEmail()),

                () -> assertNull(result.getPersonalInformation().getId()),
                () -> assertEquals(expected.getPersonalInformation().getFirstname(), result.getPersonalInformation().getFirstname()),
                () -> assertEquals(expected.getPersonalInformation().getLastname(), result.getPersonalInformation().getLastname()),
                () -> assertEquals(expected.getPersonalInformation().getBirthdate(), result.getPersonalInformation().getBirthdate()),

                () -> assertNull(result.getContactInformation().getId()),
                () -> assertEquals(expected.getContactInformation().getPhoneNumber(), result.getContactInformation().getPhoneNumber()),
                () -> assertEquals(expected.getContactInformation().getAddressLine(), result.getContactInformation().getAddressLine()),
                () -> assertEquals(expected.getContactInformation().getCity(), result.getContactInformation().getCity()),
                () -> assertEquals(expected.getContactInformation().getCountry(), result.getContactInformation().getCountry()),
                () -> assertEquals(expected.getContactInformation().getPostcode(), result.getContactInformation().getPostcode()),

                () -> assertNull(result.getWorkInformation().getId()),
                () -> assertEquals(expected.getWorkInformation().getSalary(), result.getWorkInformation().getSalary()),
                () -> assertEquals(expected.getWorkInformation().getDepartment(), result.getWorkInformation().getDepartment()),
                () -> assertEquals(expected.getWorkInformation().getStartWorkDate(), result.getWorkInformation().getStartWorkDate()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperLevel(), result.getWorkInformation().getDeveloperLevel()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTier(), result.getWorkInformation().getDeveloperTier()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTier(), result.getWorkInformation().getDeveloperTier()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTitle(), result.getWorkInformation().getDeveloperTitle()),

                () -> assertNull(result.getExpenses()),
                () -> assertNull(result.getLeaves()),
                () -> assertNull(result.getOvertimes())
        );
    }

    @Test
    void toEmployeeDto() {
        Employee employee = EmployeeUtil.generateEmployee(EMPLOYEE_ID);

        EmployeeDto expected = EmployeeUtil.generateEmployeeDto();
        EmployeeDto result = employeeMapper.toEmployeeDto(employee);

        assertAll(
                () -> assertEquals(expected.email(), result.email()),

                () -> assertEquals(expected.firstname(), result.firstname()),
                () -> assertEquals(expected.lastname(), result.lastname()),
                () -> assertEquals(expected.birthdate(), result.birthdate()),

                () -> assertEquals(expected.phoneNumber(), result.phoneNumber()),
                () -> assertEquals(expected.addressLine(), result.addressLine()),
                () -> assertEquals(expected.city(), result.city()),
                () -> assertEquals(expected.country(), result.country()),
                () -> assertEquals(expected.postcode(), result.postcode()),

                () -> assertEquals(expected.salary(), result.salary()),
                () -> assertEquals(expected.department(), result.department()),
                () -> assertEquals(expected.startWorkDate(), result.startWorkDate()),
                () -> assertEquals(expected.developerLevel(), result.developerLevel()),
                () -> assertEquals(expected.developerTier(), result.developerTier()),
                () -> assertEquals(expected.developerTitle(), result.developerTitle()),
                () -> assertEquals(expected.developerLevel(), result.developerLevel())
        );
    }

    @Test
    void updateEmployee() {
        Employee employee = EmployeeUtil.generateEmployee(EMPLOYEE_ID);
        UpdateEmployeeRequest updateEmployeeRequest = EmployeeUtil.generateUpdateEmployeeRequest();

        Employee expected = EmployeeUtil.generateEmployee2(EMPLOYEE_ID);
        Employee result = employeeMapper.updateEmployee(employee, updateEmployeeRequest);

        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getEmail(), result.getEmail()),

                () -> assertNotNull(result.getPersonalInformation().getId()),
                () -> assertEquals(expected.getPersonalInformation().getFirstname(), result.getPersonalInformation().getFirstname()),
                () -> assertEquals(expected.getPersonalInformation().getLastname(), result.getPersonalInformation().getLastname()),
                () -> assertEquals(expected.getPersonalInformation().getBirthdate(), result.getPersonalInformation().getBirthdate()),

                () -> assertNotNull(result.getContactInformation().getId()),
                () -> assertEquals(expected.getContactInformation().getPhoneNumber(), result.getContactInformation().getPhoneNumber()),
                () -> assertEquals(expected.getContactInformation().getAddressLine(), result.getContactInformation().getAddressLine()),
                () -> assertEquals(expected.getContactInformation().getCity(), result.getContactInformation().getCity()),
                () -> assertEquals(expected.getContactInformation().getCountry(), result.getContactInformation().getCountry()),
                () -> assertEquals(expected.getContactInformation().getPostcode(), result.getContactInformation().getPostcode()),

                () -> assertNotNull(result.getWorkInformation().getId()),
                () -> assertEquals(expected.getWorkInformation().getSalary(), result.getWorkInformation().getSalary()),
                () -> assertEquals(expected.getWorkInformation().getDepartment(), result.getWorkInformation().getDepartment()),
                () -> assertEquals(expected.getWorkInformation().getStartWorkDate(), result.getWorkInformation().getStartWorkDate()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperLevel(), result.getWorkInformation().getDeveloperLevel()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTier(), result.getWorkInformation().getDeveloperTier()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTier(), result.getWorkInformation().getDeveloperTier()),
                () -> assertEquals(expected.getWorkInformation().getDeveloperTitle(), result.getWorkInformation().getDeveloperTitle())
        );
    }
}
