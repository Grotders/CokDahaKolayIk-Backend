package com.fmss.cokdahakolayik.unit.overtime;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.mapper.OvertimeMapperImpl;
import com.fmss.cokdahakolayik.model.entity.Overtime;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.OvertimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OvertimeConverterTest extends BaseUnitTest {

    @InjectMocks
    OvertimeMapperImpl overtimeMapper;


    @Test
    void toOvertimeFromCreateOvertimeRequest() {
        CreateOvertimeRequest createOvertimeRequest = OvertimeUtil.generateCreateOvertimeRequest(EMPLOYEE_ID);

        Overtime expected = OvertimeUtil.generateOvertime(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Overtime result = overtimeMapper.toOvertimeFromCreateOvertimeRequest(createOvertimeRequest);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(expected.getOvertimeDate(), result.getOvertimeDate()),
                () -> assertEquals(expected.getAmountOvertime(), result.getAmountOvertime()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }

    @Test
    void toOvertimeDto() {
        Overtime overtime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));

        OvertimeDto expected = OvertimeUtil.generateOvertimeDto(OVERTIME_ID);
        OvertimeDto result = overtimeMapper.toOvertimeDto(overtime);

        assertAll(
                () -> assertEquals(expected.id(), result.id()),
                () -> assertEquals(expected.overtimeDate(), result.overtimeDate()),
                () -> assertEquals(expected.amountOvertime(), result.amountOvertime()),
                () -> assertEquals(expected.description(), result.description())
        );
    }

    @Test
    void updateOvertime() {
        Overtime overtime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        UpdateOvertimeRequest updateOvertimeRequest = OvertimeUtil.generateUpdateOvertimeRequest(EMPLOYEE_ID);

        Overtime expected = OvertimeUtil.generateOvertime2(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Overtime result = overtimeMapper.updateOvertime(overtime, updateOvertimeRequest);

        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getOvertimeDate(), result.getOvertimeDate()),
                () -> assertEquals(expected.getAmountOvertime(), result.getAmountOvertime()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }
}
