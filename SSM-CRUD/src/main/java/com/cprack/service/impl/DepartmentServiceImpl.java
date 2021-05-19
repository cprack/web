package com.cprack.service.impl;

import com.cprack.dao.DepartmentMapper;
import com.cprack.domain.Department;
import com.cprack.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper){
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> selectDepartments() {
        return departmentMapper.selectAll();
    }
}
