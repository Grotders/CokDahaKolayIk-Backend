package com.fmss.cokdahakolayik.service;

import com.fmss.cokdahakolayik.model.enums.Department;
import com.fmss.cokdahakolayik.model.enums.DeveloperLevel;
import com.fmss.cokdahakolayik.model.enums.DeveloperTier;
import com.fmss.cokdahakolayik.model.enums.DeveloperTitle;

import java.util.List;

public interface EnumService {

    List<Department> getAllDepartments();
    DeveloperLevel[] getAllDeveloperLevels();
    DeveloperTier[] getAllDeveloperTiers();
    DeveloperTitle[] getAllDeveloperTitle();

}
