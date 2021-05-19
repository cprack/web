package com.cprack.dao;

import com.cprack.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer staffId);

    int deleteStaffsByStaffName(String[] staffName);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer staffId);

    Employee selectByStaffName(String staffName);

    List<Employee> selectAll();

    Employee selectByPrimaryKeyWithDepartment(Integer staffId);

    Employee selectEmailByStaffName(String staffName);

    List<Employee> selectAllWithDepartment();

    int updateByPrimaryKey(Employee record);

    int updateByStaffName(Employee record);
}