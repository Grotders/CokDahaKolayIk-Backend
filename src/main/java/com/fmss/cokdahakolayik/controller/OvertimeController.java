package com.fmss.cokdahakolayik.controller;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.CreateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateOvertimeRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.client.dto.response.OvertimeDto;
import com.fmss.cokdahakolayik.service.OvertimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/overtimes")
@RequiredArgsConstructor
@CrossOrigin
public class OvertimeController {
    private final OvertimeService overtimeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OvertimeDto> addOvertime(@Valid @RequestBody CreateOvertimeRequest overtimeRequest) {
        return ResponseEntity.ok(overtimeService.addOvertime(overtimeRequest));
    }

    @GetMapping
    public ResponseEntity<List<OvertimeDto>> getOvertimes(@RequestParam Long employeeId, @RequestParam Integer page) {
        return ResponseEntity.ok(overtimeService.getOvertimes(employeeId, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OvertimeDto> getOvertime(@PathVariable Long id) {
        return ResponseEntity.ok(overtimeService.getOvertime(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public ResponseEntity<OvertimeDto> updateOvertime(@PathVariable Long id, @Valid @RequestBody UpdateOvertimeRequest overtimeRequest) {
        return ResponseEntity.ok(overtimeService.updateOvertime(id, overtimeRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteOvertime(@PathVariable Long id) {
        overtimeService.deleteOvertime(id);
    }
}
