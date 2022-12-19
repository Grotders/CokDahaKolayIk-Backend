package com.fmss.cokdahakolayik.mapper;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.model.entity.Leave;
import com.fmss.cokdahakolayik.model.entity.Overtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(implementationName = "OvertimeMapperImpl", componentModel = "spring", imports = {OvertimeMapper.class})
public interface OvertimeMapper {

    @Mapping(target = "employee.id", source = "employeeId")
    Overtime toOvertimeFromCreateOvertimeRequest(CreateOvertimeRequest overtimeRequest);


    @Mapping(target = "id", source = "id")
    OvertimeDto toOvertimeDto(Overtime overtime);

    Overtime updateOvertime(@MappingTarget Overtime overtime, UpdateOvertimeRequest overtimeRequest);
}
