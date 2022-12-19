package com.fmss.cokdahakolayik.service;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;

import java.util.List;

public interface LeaveService {

    LeaveDto addLeave(CreateLeaveRequest leaveRequest);
    List<LeaveDto> getLeaves(Long employeeId, Integer page);
    LeaveDto getLeave(Long leaveId);
    LeaveDto updateLeave(Long employeeId, UpdateLeaveRequest leaveRequest);
    void deleteLeave(Long leaveId);
}
