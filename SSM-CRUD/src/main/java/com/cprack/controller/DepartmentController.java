package com.cprack.controller;

import com.cprack.domain.Department;
import com.cprack.domain.Employee;
import com.cprack.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }



    @RequestMapping("/selectGid")
    @ResponseBody
    public Map<String, Object> getAll(){

        Map<String, Object> map = new HashMap<>();
        List<Department> departments = departmentService.selectDepartments();
        map.put("departments",departments);

        return map;
    }
}
