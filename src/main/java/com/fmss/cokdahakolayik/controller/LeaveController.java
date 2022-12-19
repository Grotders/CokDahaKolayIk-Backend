package com.fmss.cokdahakolayik.controller;

import com.fmss.cokdahakolayik.client.dto.request.CreateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.request.UpdateLeaveRequest;
import com.fmss.cokdahakolayik.client.dto.response.LeaveDto;
import com.fmss.cokdahakolayik.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
@CrossOrigin
public class LeaveController {
    private final LeaveService leaveService;

    @PostMapping
    public ResponseEntity<LeaveDto> addLeave(@Valid @RequestBody CreateLeaveRequest leaveRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveService.addLeave(leaveRequest));
    }

    @GetMapping
    public ResponseEntity<List<LeaveDto>> getLeaves(@RequestParam Long employeeId, @RequestParam Integer page) {
        return ResponseEntity.ok(leaveService.getLeaves(employeeId, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveDto> getLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeave(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveDto> updateLeave(@PathVariable Long id, @Valid @RequestBody UpdateLeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveService.updateLeave(id, leaveRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
    }
}
