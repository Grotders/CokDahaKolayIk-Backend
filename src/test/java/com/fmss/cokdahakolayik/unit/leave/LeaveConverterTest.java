package com.fmss.cokdahakolayik.unit.leave;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.mapper.LeaveMapperImpl;
import com.fmss.cokdahakolayik.model.entity.Leave;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.LeaveUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LeaveConverterTest extends BaseUnitTest {

    @InjectMocks
    LeaveMapperImpl leaveMapper;


    @Test
    void toLeaveFromCreateLeaveRequest() {
        CreateLeaveRequest createLeaveRequest = LeaveUtil.generateCreateLeaveRequest(EMPLOYEE_ID);

        Leave expected = LeaveUtil.generateLeave(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Leave result = leaveMapper.toLeaveFromCreateLeaveRequest(createLeaveRequest);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(expected.getType(), result.getType()),
                () -> assertEquals(expected.getStartDate(), result.getStartDate()),
                () -> assertEquals(expected.getEndDate(), result.getEndDate()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getTotalDay(), result.getTotalDay()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }

    @Test
    void toLeaveDto() {
        Leave leave = LeaveUtil.generateLeave(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));

        LeaveDto expected = LeaveUtil.generateLeaveDto(LEAVE_ID);
        LeaveDto result = leaveMapper.toLeaveDto(leave);

        assertAll(
                () -> assertEquals(expected.id(), result.id()),
                () -> assertEquals(expected.type(), result.type()),
                () -> assertEquals(expected.startDate(), result.startDate()),
                () -> assertEquals(expected.endDate(), result.endDate()),
                () -> assertEquals(expected.description(), result.description()),
                () -> assertEquals(expected.totalDay(), result.totalDay())
        );
    }

    @Test
    void updateLeave() {
        Leave leave = LeaveUtil.generateLeave2(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        UpdateLeaveRequest updateLeaveRequest = LeaveUtil.generateUpdateLeaveRequest(LEAVE_ID);

        Leave expected = LeaveUtil.generateLeave2(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Leave result = leaveMapper.updateLeave(leave, updateLeaveRequest);

        assertAll(
                () -> assertEquals(expected.getId(), result.getId()),
                () -> assertEquals(expected.getType(), result.getType()),
                () -> assertEquals(expected.getStartDate(), result.getStartDate()),
                () -> assertEquals(expected.getEndDate(), result.getEndDate()),
                () -> assertEquals(expected.getDescription(), result.getDescription()),
                () -> assertEquals(expected.getTotalDay(), result.getTotalDay()),
                () -> assertEquals(expected.getEmployee().getId(), result.getEmployee().getId())
        );
    }
}
