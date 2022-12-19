package com.fmss.cokdahakolayik.unit.overtime;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.OvertimeMapper;
import com.fmss.cokdahakolayik.model.entity.Overtime;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.OvertimeRepository;
import com.fmss.cokdahakolayik.service.impl.OvertimeServiceImpl;
import com.fmss.cokdahakolayik.unit.BaseUnitTest;
import com.fmss.cokdahakolayik.util.EmployeeUtil;
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
public class OvertimeServiceTest extends BaseUnitTest {

    @InjectMocks
    OvertimeServiceImpl overtimeService;

    @Mock
    OvertimeRepository overtimeRepository;

    @Mock
    OvertimeMapper overtimeMapper;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void addOvertime_EmployeeExistInDatabase() {
        CreateOvertimeRequest createOvertimeRequest = OvertimeUtil.generateCreateOvertimeRequest(EMPLOYEE_ID);
        Overtime preSaveOvertime = OvertimeUtil.generateOvertime(null, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Overtime postSaveOvertime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        OvertimeDto overtimeDto = OvertimeUtil.generateOvertimeDto(OVERTIME_ID);

        when(employeeRepository.existsById(any())).thenReturn(true);
        when(overtimeMapper.toOvertimeFromCreateOvertimeRequest(any())).thenReturn(preSaveOvertime);
        when(overtimeRepository.save(any())).thenReturn(postSaveOvertime);
        when(overtimeMapper.toOvertimeDto(any())).thenReturn(overtimeDto);

        OvertimeDto result = overtimeService.addOvertime(createOvertimeRequest);
        assertSame(overtimeDto, result);

        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verify(overtimeMapper).toOvertimeFromCreateOvertimeRequest(createOvertimeRequest);
        verify(overtimeRepository).save(preSaveOvertime);
        verify(overtimeMapper).toOvertimeDto(postSaveOvertime);
    }

    @Test
    void addOvertime_EmployeeDoesNotExistInDatabase_GeneralException() {
        CreateOvertimeRequest createOvertimeRequest = OvertimeUtil.generateCreateOvertimeRequest(EMPLOYEE_ID);
        when(employeeRepository.existsById(any())).thenReturn(false);
        assertThrows(GeneralException.class, () -> overtimeService.addOvertime(createOvertimeRequest));
        verify(employeeRepository).existsById(EMPLOYEE_ID);
        verifyNoInteractions(overtimeMapper);
    }

    @Test
    void getOvertimes_PageDoesHaveOvertime() {
        Integer page = 1;

        Overtime overtime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        List<Overtime> overtimes = List.of(overtime);
        OvertimeDto overtimeDto = OvertimeUtil.generateOvertimeDto(OVERTIME_ID);
        List<OvertimeDto> overtimeDtos = List.of(overtimeDto);

        when(overtimeRepository.findAllByEmployee_Id(any(), any())).thenReturn(overtimes);
        when(overtimeMapper.toOvertimeDto(any())).thenReturn(overtimeDto);

        List<OvertimeDto> result = overtimeService.getOvertimes(EMPLOYEE_ID, page);

        assertEquals(overtimeDtos, result);
        verify(overtimeRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(0, 5));
        verify(overtimeMapper).toOvertimeDto(overtime);
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,4})
    void getOvertimes_PageNotDoesHaveOvertime_GeneralException(Integer page) {
        List<Overtime> overtimes = List.of();
        when(overtimeRepository.findAllByEmployee_Id(any(), any())).thenReturn(overtimes);
        assertThrows(GeneralException.class, () -> overtimeService.getOvertimes(EMPLOYEE_ID, page));
        verify(overtimeRepository).findAllByEmployee_Id(EMPLOYEE_ID, PageRequest.of(page-1, 5));
        verifyNoInteractions(overtimeMapper);
    }

    @Test
    void getOvertime_OvertimeFoundInDatabase() {
        Overtime overtime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        OvertimeDto overtimeDto = OvertimeUtil.generateOvertimeDto(EMPLOYEE_ID);
        when(overtimeRepository.findById(any())).thenReturn(Optional.of(overtime));
        when(overtimeMapper.toOvertimeDto(any())).thenReturn(overtimeDto);
        OvertimeDto result = overtimeService.getOvertime(OVERTIME_ID);
        assertEquals(overtimeDto, result);
        verify(overtimeRepository).findById(OVERTIME_ID);
        verify(overtimeMapper).toOvertimeDto(overtime);
    }

    @Test
    void getOvertime_OvertimeDoesNotFindInDatabase_GeneralException() {
        assertThrows(GeneralException.class, () -> overtimeService.getOvertime(OVERTIME_ID));
        verify(overtimeRepository).findById(OVERTIME_ID);
        verifyNoInteractions(overtimeMapper);
    }

    @Test
    void updateOvertime_ValidInput() {
        UpdateOvertimeRequest updateOvertimeRequest = OvertimeUtil.generateUpdateOvertimeRequest(EMPLOYEE_ID);
        Overtime overtime = OvertimeUtil.generateOvertime(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        Overtime updatedOvertime = OvertimeUtil.generateOvertime2(OVERTIME_ID, EmployeeUtil.generateEmployee(EMPLOYEE_ID));
        OvertimeDto overtimeDto = OvertimeUtil.generateOvertimeDto2(OVERTIME_ID);

        when(overtimeRepository.findById(any())).thenReturn(Optional.of(overtime));
        when(overtimeMapper.updateOvertime(any(), any())).thenReturn(updatedOvertime);
        when(overtimeRepository.save(any())).thenReturn(updatedOvertime);
        when(overtimeMapper.toOvertimeDto(any())).thenReturn(overtimeDto);

        OvertimeDto result = overtimeService.updateOvertime(OVERTIME_ID, updateOvertimeRequest);
        assertEquals(overtimeDto, result);

        verify(overtimeRepository).findById(OVERTIME_ID);
        verify(overtimeMapper).updateOvertime(overtime, updateOvertimeRequest);
        verify(overtimeRepository).save(updatedOvertime);
        verify(overtimeMapper).toOvertimeDto(updatedOvertime);
    }

    @Test
    void updateOvertime_DoesNotFindInDatabase_GeneralException() {
        UpdateOvertimeRequest updateOvertimeRequest = OvertimeUtil.generateUpdateOvertimeRequest(EMPLOYEE_ID);
        assertThrows(GeneralException.class, () -> overtimeService.updateOvertime(OVERTIME_ID, updateOvertimeRequest));
        verify(overtimeRepository).findById(OVERTIME_ID);
        verifyNoInteractions(overtimeMapper);
        verify(overtimeRepository, never()).save(any());
    }

    @Test
    void deleteOvertime_OvertimeFoundInDatabase() {
        when(overtimeRepository.existsById(OVERTIME_ID)).thenReturn(true);
        overtimeService.deleteOvertime(OVERTIME_ID);
        verify(overtimeRepository).existsById(OVERTIME_ID);
        verify(overtimeRepository).deleteById(OVERTIME_ID);
    }

    @Test
    void deleteOvertime_OvertimeDoesNotFindInDatabase_GeneralException() {
        when(overtimeRepository.existsById(OVERTIME_ID)).thenReturn(false);
        assertThrows(GeneralException.class, () -> overtimeService.deleteOvertime(OVERTIME_ID));
        verify(overtimeRepository).existsById(OVERTIME_ID);
        verify(overtimeRepository, never()).deleteById(OVERTIME_ID);
    }
}
