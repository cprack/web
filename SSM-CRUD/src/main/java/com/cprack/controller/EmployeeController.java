package com.cprack.controller;

import com.cprack.domain.Employee;
import com.cprack.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工的CRUD请求
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    /**
     * 查询员工数据、分页查询
     * @return
     */
    @RequestMapping("/emp")
    public String getEmp(){
        return "list";
    }

    @RequestMapping("/gets")
    @ResponseBody
    public Map<String, Object> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){

        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);

        List<Employee> employees = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employees);
        pageInfo.setNavigatePages(5);
        map.put("info",pageInfo);
        return map;
    }

    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    @ResponseBody
    public int insertEmp(Employee employee){
        return employeeService.insertEmp(employee);
    }

    @RequestMapping(value = "/selectEmp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> selectEmp(@RequestParam(value = "staffName")String staffName){
        Map<String, Boolean> map = new HashMap<>();
        if(employeeService.selectEmp(staffName) != null)
            map.put("valid",false);
        else
            map.put("valid",true);
        return map;
    }

    @RequestMapping(value = "/selectStaff",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectStaff(@RequestParam(value = "staffId")Integer staffId){
        Map<String, Object> map = new HashMap<>();
        Employee employee = employeeService.selectStaff(staffId);
        map.put("info",employee);
        return map;
    }

    @RequestMapping(value = "/selectEmail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectEmail(@RequestParam(value = "staffName")String staffName){
        Map<String, Object> map = new HashMap<>();
        Employee employee = employeeService.selectEmail(staffName);
        map.put("info",employee);
        return map;
    }

    @RequestMapping(value = "/updateStaff",method = RequestMethod.POST)
    @ResponseBody
    public int updateStaff(Employee employee){
        return employeeService.updateByStaffName(employee);
    }

    @RequestMapping(value = "/deleteStaff",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteStaff(@RequestParam(value = "staffId")Integer staffId){
        return employeeService.deleteStaff(staffId);
    }

    @RequestMapping(value = "/deleteStaffs",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteStaffs(@RequestParam(value = "staffName")String staffName){
        return employeeService.deleteStaffs(staffName.split(","));
    }
}
