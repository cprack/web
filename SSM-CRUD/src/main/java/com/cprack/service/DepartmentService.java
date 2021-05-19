package com.cprack.service;

import com.cprack.domain.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> selectDepartments();
}
