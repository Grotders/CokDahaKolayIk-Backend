package com.fmss.cokdahakolayik.service.impl;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.configuration.aspect.ToLog;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.LeaveMapper;
import com.fmss.cokdahakolayik.model.entity.Leave;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.LeaveRepository;
import com.fmss.cokdahakolayik.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    private final EmployeeRepository employeeRepository;

    @ToLog
    @Override
    public LeaveDto addLeave(CreateLeaveRequest leaveRequest){
        boolean doesExist = this.employeeRepository.existsById(leaveRequest.employeeId());
        if (!doesExist)
            throw new GeneralException("İzin eklenecek çalışan bulunamadı.");

        long totalDay = calculateTotalDate(leaveRequest.startDate(), leaveRequest.endDate());
        Leave leave = this.leaveMapper.toLeaveFromCreateLeaveRequest(leaveRequest);
        leave.setTotalDay(totalDay);
        leave = this.leaveRepository.save(leave);
        return this.leaveMapper.toLeaveDto(leave);
    }

    private long calculateTotalDate(LocalDate startDate, LocalDate endDate){
        DayOfWeek startW = startDate.getDayOfWeek();
        DayOfWeek endW = endDate.getDayOfWeek();

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        long daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);
        long totalDay = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
        if (totalDay < 0) {
            log.error("İzin bitiş tarihi başlangıç tarihinden sonra olmalı.");
            throw new GeneralException("İzin bitiş tarihi başlangıç tarihinden sonra olmalı.");
        }
        return totalDay;
    }

    @Override
    public List<LeaveDto> getLeaves(Long employeeId, Integer page) {
        List<LeaveDto> leaves = this.leaveRepository.findAllByEmployee_Id(employeeId, PageRequest.of(page-1, 5))
                .stream()
                .map(this.leaveMapper::toLeaveDto)
                .toList();

        if (leaves.isEmpty())
            throw new GeneralException("İzinler bulunamadı.");
        return leaves;
    }

    @ToLog
    @Override
    public LeaveDto getLeave(Long leaveId) {
        return this.leaveRepository.findById(leaveId).map(leaveMapper::toLeaveDto)
                .orElseThrow(() -> new GeneralException("İzin Bulunamadı"));
    }

    @ToLog
    @Override
    public LeaveDto updateLeave(Long leaveId, UpdateLeaveRequest leaveRequest) {
        Leave updatedLeave = this.leaveRepository.findById(leaveId)
                .map(leave -> this.leaveMapper.updateLeave(leave, leaveRequest))
                .orElseThrow(() -> new GeneralException("İzin güncellerken hata oluştur."));
        long totalDay = calculateTotalDate(updatedLeave.getStartDate(), updatedLeave.getEndDate());
        updatedLeave.setTotalDay(totalDay);
        updatedLeave = this.leaveRepository.save(updatedLeave);
//        updatedLeave = this.leaveRepository.updateLeaveById(leaveId, updatedLeave);
        return this.leaveMapper.toLeaveDto(updatedLeave);
    }

    @ToLog
    @Override
    public void deleteLeave(Long leaveId) {
        boolean doesExist = this.leaveRepository.existsById(leaveId);
        if (!doesExist)
            throw new GeneralException("Silinecek izin bulunamadı.");
        this.leaveRepository.deleteById(leaveId);
    }
}
