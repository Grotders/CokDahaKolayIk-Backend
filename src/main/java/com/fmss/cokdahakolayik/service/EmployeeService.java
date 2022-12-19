package com.fmss.cokdahakolayik.service;

import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.response.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(CreateEmployeeRequest employeeRequest);
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployee(Long id);

    EmployeeDto updateEmployee(Long id, UpdateEmployeeRequest employeeRequest);

    void deleteEmployee(Long id);
}
