package com.fmss.cokdahakolayik.controller;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import com.fmss.cokdahakolayik.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enums")
@RequiredArgsConstructor
@CrossOrigin
public class EnumController {

    private final EnumService enumService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(enumService.getAllDepartments());
    }

    @GetMapping("/levels")
    public ResponseEntity<DeveloperLevel[]> getAllDeveloperLevels() {
        return ResponseEntity.ok(enumService.getAllDeveloperLevels());
    }
    @GetMapping("/tiers")
    public ResponseEntity<DeveloperTier[]> getAllDeveloperTiers() {
        return ResponseEntity.ok(enumService.getAllDeveloperTiers());
    }
    @GetMapping("/titles")
    public ResponseEntity<DeveloperTitle[]> getAllDeveloperTitles() {
        return ResponseEntity.ok(enumService.getAllDeveloperTitle());
    }
}
