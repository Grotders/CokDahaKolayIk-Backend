package com.fmss.cokdahakolayik.mapper;

import com.fmss.cokdahakolayik.client.dto.request.CreateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateEmployeeRequest;
import com.fmss.cokdahakolayik.client.dto.response.EmployeeDto;
import com.fmss.cokdahakolayik.model.entity.*;
import org.mapstruct.*;


@Mapper(implementationName = "EmployeeMapperImpl", componentModel = "spring", imports = {Employee.class, PersonalInformation.class, WorkInformation.class, ContactInformation.class})
public interface EmployeeMapper{

//    @Mapping(target = "contactInformation",
//            expression = "java(toContactInformationFromCreateEmployeeRequest(employeeRequest))")
//    @Mapping(target = "workInformation",
//            expression = "java(toWorkInformationFromCreateEmployeeRequest(employeeRequest))")
//    @Mapping(target = "personalInformation",
//            expression = "java(employeeRequest.citizenId() != null?toPersonalInformationFromCreateEmployeeRequest(employeeRequest):null)")

    @Mapping(target = "contactInformation", source = ".")
    @Mapping(target = "workInformation", source = ".")
    @Mapping(target = "personalInformation", source = ".")
    Employee toEmployeeFromCreateEmployeeRequest(CreateEmployeeRequest employeeRequest);


    @Mapping(target = ".", source = "workInformation")
    @Mapping(target = ".", source = "contactInformation")
    @Mapping(target = ".", source = "personalInformation")
    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(target = "contactInformation", source = ".")
    @Mapping(target = "workInformation", source = ".")
    @Mapping(target = "personalInformation", source = ".")
    Employee updateEmployee(@MappingTarget Employee employee, UpdateEmployeeRequest employeeRequest);
}
