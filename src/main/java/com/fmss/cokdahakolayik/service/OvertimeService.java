package com.fmss.cokdahakolayik.service;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;

import java.util.List;

public interface OvertimeService {

    OvertimeDto addOvertime(CreateOvertimeRequest overtimeRequest);
    List<OvertimeDto> getOvertimes(Long employeeId, Integer page);
    OvertimeDto getOvertime(Long overtimeId);
    OvertimeDto updateOvertime(Long employeeId, UpdateOvertimeRequest overtimeRequest);
    void deleteOvertime(Long overtimeId);

}
