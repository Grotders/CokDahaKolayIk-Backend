package com.fmss.cokdahakolayik.service.impl;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;
import com.fmss.cokdahakolayik.service.EnumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EnumServiceImpl implements EnumService {

    @Override
    public List<Department> getAllDepartments() {
        return List.of(Department.values());
    }

    @Override
    public DeveloperLevel[] getAllDeveloperLevels() {
        return DeveloperLevel.values();
    }

    @Override
    public DeveloperTier[] getAllDeveloperTiers() {
        return DeveloperTier.values();
    }

    @Override
    public DeveloperTitle[] getAllDeveloperTitle() {
        return DeveloperTitle.values();
    }
}
