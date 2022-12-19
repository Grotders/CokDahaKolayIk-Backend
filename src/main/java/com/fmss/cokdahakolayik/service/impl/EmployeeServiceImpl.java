package com.fmss.cokdahakolayik.service.impl;

import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.response.EmployeeDto;
import com.fmss.cokdahakolayik.configuration.aspect.ToLog;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.EmployeeMapper;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @ToLog
    @Override
    public EmployeeDto createEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = this.employeeMapper.toEmployeeFromCreateEmployeeRequest(employeeRequest);
        this.employeeRepository.save(employee);
        return this.employeeMapper.toEmployeeDto(employee);
    }
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = this.employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();

        if (employees.isEmpty())
            throw new GeneralException("Harcamalar bulunamadı.");
        return employees;
    }
    @ToLog
    @Override
    public EmployeeDto getEmployee(Long id) {
        return this.employeeRepository.findById(id)
                .map(employeeMapper::toEmployeeDto)
                .orElseThrow(() -> new GeneralException("Calisan bulunamadi"));
    }
    @ToLog
    @Override
    public EmployeeDto updateEmployee(Long id, UpdateEmployeeRequest employeeRequest) {
        Employee updatedEmployee = this.employeeRepository.findById(id)
                .map(employee -> this.employeeMapper.updateEmployee(employee, employeeRequest))
                        .orElseThrow(() -> new GeneralException("Çalışan güncellenemedi"));
        this.employeeRepository.save(updatedEmployee);
        return this.employeeMapper.toEmployeeDto(updatedEmployee);
    }
    @ToLog
    @Override
    public void deleteEmployee(Long id) {
        boolean doesExist = this.employeeRepository.existsById(id);
        if (!doesExist)
            throw new GeneralException("Silinecek çalışan bulunamadı.");
        this.employeeRepository.deleteById(id);
    }
}
