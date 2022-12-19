package com.fmss.cokdahakolayik.util;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.model.entity.Leave;

import java.time.LocalDate;

public class LeaveUtil extends BaseUtil {

    // Leave ####################################################################################################
    // 2 Create, 1 Update, 2 entity, 2 Response

    public static CreateLeaveRequest generateCreateLeaveRequest(Long employeeId) {
        return CreateLeaveRequest.builder()
                .employeeId(employeeId)
                .type("Sağlık")
                .startDate(getDate)
                .endDate(getDate.plusDays(5))
                .description("Covid oldum")
                .build();
    }

//    public CreateLeaveRequest generateCreateLeaveRequest2(Long employeeId) {
//        return CreateLeaveRequest.builder()
//                .employeeId(employeeId)
//                .type("Yıllık izin")
//                .startDate(getDate.plusDays(15))
//                .endDate(getDate.plusDays(29))
//                .description("Yıllık iznimin hepsini kullanacağım")
//                .build();
//    }

    public static UpdateLeaveRequest generateUpdateLeaveRequest(Long employeeId) {
        return UpdateLeaveRequest.builder()
                .employeeId(employeeId)
                .type("Sağlık(Updated)").startDate(getDate)
                .endDate(getDate.plusDays(3))
                .description("Covid oldum")
                .build();
    }

    public static Leave generateLeave(Long id, Employee employee) {
        Leave leave = new Leave();
        leave.setId(id);
        leave.setType("Sağlık");
        leave.setStartDate(getDate);
        leave.setEndDate(getDate.plusDays(5));
        leave.setDescription("Covid oldum");
        leave.setEmployee(employee);
        return leave;
    }

    public static Leave generateLeave2(Long id, Employee employee) {
        Leave leave = new Leave();
        leave.setId(id);
        leave.setType("Sağlık(Updated)");
        leave.setStartDate(getDate);
        leave.setEndDate(getDate.plusDays(3));
        leave.setDescription("Covid oldum");
        leave.setEmployee(employee);
        return leave;
    }

    public static LeaveDto generateLeaveDto(Long id) {
        return LeaveDto.builder()
                .id(id)
                .type("Sağlık")
                .startDate(getDate)
                .endDate(getDate.plusDays(5))
                .description("Covid oldum")
                .build();
    }

    public static LeaveDto generateLeaveDto2(Long id) {
        return LeaveDto.builder()
                .id(id)
                .type("Sağlık(Updated)")
                .startDate(getDate)
                .endDate(getDate.plusDays(3))
                .description("Covid oldum")
                .build();
    }
}
