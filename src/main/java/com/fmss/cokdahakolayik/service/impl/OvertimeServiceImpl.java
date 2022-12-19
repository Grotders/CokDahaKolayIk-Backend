package com.fmss.cokdahakolayik.service.impl;

import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.configuration.aspect.ToLog;
import com.fmss.cokdahakolayik.exception.GeneralException;
import com.fmss.cokdahakolayik.mapper.OvertimeMapper;
import com.fmss.cokdahakolayik.model.entity.Overtime;
import com.fmss.cokdahakolayik.reposity.EmployeeRepository;
import com.fmss.cokdahakolayik.reposity.OvertimeRepository;
import com.fmss.cokdahakolayik.service.OvertimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OvertimeServiceImpl implements OvertimeService {

    private final OvertimeRepository overtimeRepository;
    private final OvertimeMapper overtimeMapper;
    private final EmployeeRepository employeeRepository;

    @ToLog
    @Override
    public OvertimeDto addOvertime(CreateOvertimeRequest overtimeRequest) {
        boolean doesExist = this.employeeRepository.existsById(overtimeRequest.employeeId());
        if (!doesExist)
            throw new GeneralException("Mesai eklenecek çalışan bulunamadı.");

        return overtimeMapper.toOvertimeDto(
                overtimeRepository.save(
                        overtimeMapper.toOvertimeFromCreateOvertimeRequest(overtimeRequest))
        );
    }

    @Override
    public List<OvertimeDto> getOvertimes(Long employeeId, Integer page) {
        List<OvertimeDto> overtimes = overtimeRepository.findAllByEmployee_Id(employeeId, PageRequest.of(page-1, 5)).stream()
                .map(this.overtimeMapper::toOvertimeDto)
                .toList();

        if (overtimes.isEmpty())
            throw new GeneralException("Mesailer Bulunamadi.");
        return overtimes;
    }

    @ToLog
    @Override
    public OvertimeDto getOvertime(Long overtimeId) {
        return overtimeRepository.findById(overtimeId).map(overtimeMapper::toOvertimeDto)
                .orElseThrow(() -> new GeneralException("Mesai Bulunamadi."));
    }

    @ToLog
    @Override
    public OvertimeDto updateOvertime(Long overtimeId, UpdateOvertimeRequest overtimeRequest) {
        Overtime updatedOvertime = overtimeRepository.findById(overtimeId)
                .map(overtime -> overtimeMapper.updateOvertime(overtime, overtimeRequest))
                .orElseThrow(() -> new GeneralException("Mesai güncellenemedi."));
        updatedOvertime = this.overtimeRepository.save(updatedOvertime);
//        updatedOvertime = this.overtimeRepository.updateOvertimeById(overtimeId, updatedOvertime);
        return overtimeMapper.toOvertimeDto(updatedOvertime);
    }

    @ToLog
    @Override
    public void deleteOvertime(Long overtimeId) {
        boolean doesExist = this.overtimeRepository.existsById(overtimeId);
        if (!doesExist)
            throw new GeneralException("Silinecek mesai bulunamadı.");
        overtimeRepository.deleteById(overtimeId);
    }
}
