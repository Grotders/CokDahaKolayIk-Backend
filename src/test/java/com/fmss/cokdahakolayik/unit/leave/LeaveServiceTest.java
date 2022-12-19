package com.fmss.cokdahakolayik.unit.leave;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.LeaveMapperImpl;
import com.fmss.cokdahakolayik.model.entity.Leave;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.LeaveRepository;
import com.fmss.cokdahakolayik.service.impl.LeaveServiceImpl;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
import com.fmss.cokdahakolayik.util.LeaveUtil;
import com.fmss.cokdahakolayik.util.OvertimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaveServiceTest extends BaseUnitTest {

    @InjectMocks
    LeaveServiceImpl leaveService;

    @Mock
    LeaveMapperImpl leaveMapper;

    @Mock
    LeaveRepository leaveRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void addLeave_EmployeeExistInDatabase() {
        CreateLeaveRequest createLeaveRequest = LeaveUtil.generateCreateLeaveRequest(EMPLOYEE_ID);
        Leave preSaveLeave = LeaveUtil.generateLeave(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Leave postSaveLeave = LeaveUtil.generateLeave(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        LeaveDto leaveDto = LeaveUtil.generateLeaveDto(LEAVE_ID);

        when(employeeRepository.existsById(any())).thenReturn(true);
        when(leaveMapper.toLeaveFromCreateLeaveRequest(any())).thenReturn(preSaveLeave);
        when(leaveRepository.save(any())).thenReturn(postSaveLeave);
        when(leaveMapper.toLeaveDto(any())).thenReturn(leaveDto);

        LeaveDto result = leaveService.addLeave(createLeaveRequest);
        assertSame(leaveDto, result);

        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verify(leaveMapper).toLeaveFromCreateLeaveRequest(createLeaveRequest);
        verify(leaveRepository).save(preSaveLeave);
        verify(leaveMapper).toLeaveDto(postSaveLeave);
    }

    @Test
    void addLeave_EmployeeDoesNotExistInDatabase_GeneralException() {
        CreateLeaveRequest createLeaveRequest = LeaveUtil.generateCreateLeaveRequest(EMPLOYEE_ID);
        when(employeeRepository.existsById(any())).thenReturn(false);
        assertThrows(GeneralException.class, () -> leaveService.addLeave(createLeaveRequest));
        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verifyNoInteractions(leaveMapper);
    }

    @Test
    void getLeaves_PageDoesHaveLeave() {
        Integer page = 1;

        Leave leave = LeaveUtil.generateLeave(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        List<Leave> leaves = List.of(leave);
        LeaveDto leaveDto = LeaveUtil.generateLeaveDto(LEAVE_ID);
        List<LeaveDto> leaveDtos = List.of(leaveDto);

        when(leaveRepository.findAllByEmployee_Id(any(), any())).thenReturn(leaves);
        when(leaveMapper.toLeaveDto(any())).thenReturn(leaveDto);

        List<LeaveDto> result = leaveService.getLeaves(EMPLOYEE_ID, page);

        assertEquals(leaveDtos, result);
        verify(leaveRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(0, 5));
        verify(leaveMapper).toLeaveDto(leave);
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4})
    void getLeaves_PageNotDoesHaveLeave_GeneralException(Integer page) {
        List<Leave> leaves = List.of();
        when(leaveRepository.findAllByEmployee_Id(any(), any())).thenReturn(leaves);
        assertThrows(GeneralException.class, () -> leaveService.getLeaves(EMPLOYEE_ID, page));
        verify(leaveRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(page-1, 5));
        verifyNoInteractions(leaveMapper);
    }

    @Test
    void getLeave_LeaveFoundInDatabase() {
        Leave leave = LeaveUtil.generateLeave(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        LeaveDto leaveDto = LeaveUtil.generateLeaveDto(EMPLOYEE_ID);
        when(leaveRepository.findById(any())).thenReturn(Optional.of(leave));
        when(leaveMapper.toLeaveDto(any())).thenReturn(leaveDto);
        LeaveDto result = leaveService.getLeave(LEAVE_ID);
        assertEquals(leaveDto, result);
        verify(leaveRepository).findById(LEAVE_ID);
        verify(leaveMapper).toLeaveDto(leave);
    }

    @Test
    void getLeave_LeaveDoesNotFindInDatabase_GeneralException() {
        assertThrows(GeneralException.class, () -> leaveService.getLeave(LEAVE_ID));
        verify(leaveRepository).findById(LEAVE_ID);
        verifyNoInteractions(leaveMapper);
    }

    @Test
    void updateLeave_ValidInput() {
        UpdateLeaveRequest updateLeaveRequest = LeaveUtil.generateUpdateLeaveRequest(EMPLOYEE_ID);
        Leave leave = LeaveUtil.generateLeave(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Leave updatedLeave = LeaveUtil.generateLeave2(LEAVE_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        LeaveDto leaveDto = LeaveUtil.generateLeaveDto2(LEAVE_ID);

        when(leaveRepository.findById(any())).thenReturn(Optional.of(leave));
        when(leaveMapper.updateLeave(any(), any())).thenReturn(updatedLeave);
        when(leaveRepository.updateLeaveById(LEAVE_ID, updatedLeave)).thenReturn(updatedLeave);
        when(leaveMapper.toLeaveDto(any())).thenReturn(leaveDto);

        LeaveDto result = leaveService.updateLeave(LEAVE_ID, updateLeaveRequest);
        assertEquals(leaveDto, result);

        verify(leaveRepository).findById(LEAVE_ID);
        verify(leaveMapper).updateLeave(leave, updateLeaveRequest);
        verify(leaveRepository).updateLeaveById(LEAVE_ID, updatedLeave);
        verify(leaveMapper).toLeaveDto(updatedLeave);
    }

    @Test
    void updateLeave_DoesNotFindInDatabase_GeneralException() {
        UpdateLeaveRequest updateLeaveRequest = LeaveUtil.generateUpdateLeaveRequest(EMPLOYEE_ID);
        assertThrows(GeneralException.class, () -> leaveService.updateLeave(LEAVE_ID, updateLeaveRequest));
        verify(leaveRepository).findById(LEAVE_ID);
        verifyNoInteractions(leaveMapper);
        verify(leaveRepository, never()).updateLeaveById(any(), any());
    }

    @Test
    void deleteLeave_LeaveFoundInDatabase() {
        when(leaveRepository.existsById(LEAVE_ID)).thenReturn(true);
        leaveService.deleteLeave(LEAVE_ID);
        verify(leaveRepository).existsById(LEAVE_ID);
        verify(leaveRepository).deleteById(LEAVE_ID);
    }

    @Test
    void deleteLeave_LeaveDoesNotFindInDatabase_GeneralException() {
        when(leaveRepository.existsById(LEAVE_ID)).thenReturn(false);
        assertThrows(GeneralException.class, () -> leaveService.deleteLeave(LEAVE_ID));
        verify(leaveRepository).existsById(LEAVE_ID);
        verify(leaveRepository, never()).deleteById(LEAVE_ID);
    }
}
