package com.cprack.service;

import com.cprack.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    int insertEmp(Employee employee);
    Employee selectEmp(String staffName);
    Employee selectStaff(Integer staffId);
    Employee selectEmail(String staffName);
    int updateByStaffName(Employee employee);
    int deleteStaff(Integer staffId);
    int deleteStaffs(String[] staffName);
}
