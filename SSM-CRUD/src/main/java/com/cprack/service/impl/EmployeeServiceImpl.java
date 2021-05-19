package com.cprack.service.impl;

import com.cprack.dao.EmployeeMapper;
import com.cprack.domain.Employee;
import com.cprack.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper){
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectAllWithDepartment();
    }

    @Override
    public int insertEmp(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public Employee selectEmp(String staffName) {
        return employeeMapper.selectByStaffName(staffName);
    }

    @Override
    public Employee selectStaff(Integer staffId) {
        return employeeMapper.selectByPrimaryKey(staffId);
    }

    @Override
    public Employee selectEmail(String staffName) {
        return employeeMapper.selectEmailByStaffName(staffName);
    }

    @Override
    public int updateByStaffName(Employee employee) {
        return employeeMapper.updateByStaffName(employee);
    }

    @Override
    public int deleteStaff(Integer staffId) {
        return employeeMapper.deleteByPrimaryKey(staffId);
    }

    @Override
    public int deleteStaffs(String[] staffName) {
        return employeeMapper.deleteStaffsByStaffName(staffName);
    }
}
