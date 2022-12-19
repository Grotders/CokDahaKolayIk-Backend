package com.fmss.cokdahakolayik.mapper;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(implementationName = "LeaveMapperImpl", componentModel = "spring", imports = {Leave.class})

public interface LeaveMapper {

    @Mapping(target = "employee.id", source = "employeeId")
    Leave toLeaveFromCreateLeaveRequest(CreateLeaveRequest leaveRequest);

    @Mapping(target = "id", source = "id")
    LeaveDto toLeaveDto(Leave leave);

    Leave updateLeave(@MappingTarget Leave leave, UpdateLeaveRequest leaveRequest);
}
