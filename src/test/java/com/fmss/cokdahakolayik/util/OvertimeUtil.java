package com.fmss.cokdahakolayik.util;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.model.entity.Employee;
import com.fmss.cokdahakolayik.model.entity.Overtime;

import java.time.LocalDate;

public class OvertimeUtil extends BaseUtil {

    // Overtime ####################################################################################################
    // 2 Create, 1 Update, 2 entity, 2 Response

    public static CreateOvertimeRequest generateCreateOvertimeRequest(Long employeeId) {
        return CreateOvertimeRequest.builder()
                .employeeId(employeeId)
                .amountOvertime(5.0)
                .description("Alım günü")
                .overtimeDate(getDate)
                .build();
    }

//    public CreateOvertimeRequest generateCreateOvertimeRequest2(Long employeeId) {
//        return CreateOvertimeRequest.builder()
//                .employeeId(employeeId)
//                .amountOvertime(3.0)
//                .description("Bugfix")
//                .overtimeDate(getDate)
//                .build();
//    }

    public static UpdateOvertimeRequest generateUpdateOvertimeRequest(Long employeeId) {
        return UpdateOvertimeRequest.builder()
                .employeeId(employeeId)
                .amountOvertime(10.0)
                .description("Alım günü(Updated)")
                .overtimeDate(getDate)
                .build();
    }

    public static Overtime generateOvertime(Long id, Employee employee) {
        Overtime overtime = new Overtime();
        overtime.setId(id);
        overtime.setAmountOvertime(5.0);
        overtime.setDescription("Alım günü");
        overtime.setOvertimeDate(getDate);
        overtime.setEmployee(employee);
        return overtime;
    }

    public static Overtime generateOvertime2(Long id, Employee employee) {
        Overtime overtime = new Overtime();
        overtime.setId(id);
        overtime.setAmountOvertime(10.0);
        overtime.setDescription("Alım günü(Updated)");
        overtime.setOvertimeDate(getDate);
        overtime.setEmployee(employee);
        return overtime;
    }

    public static OvertimeDto generateOvertimeDto(Long id) {
        return OvertimeDto.builder()
                .id(id)
                .amountOvertime(5.0)
                .description("Alım günü")
                .overtimeDate(getDate)
                .build();
    }

    public static OvertimeDto generateOvertimeDto2(Long id) {
        return OvertimeDto.builder()
                .id(id)
                .amountOvertime(10.0)
                .description("Alım günü(Update)")
                .overtimeDate(getDate)
                .build();
    }
}
