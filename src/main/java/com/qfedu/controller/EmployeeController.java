package com.qfedu.controller;

import com.qfedu.common.JsonBean;
import com.qfedu.pojo.Employee;
import com.qfedu.service.EmployeeService;
import com.qfedu.vo.VPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @RequestMapping("/list.do")
    public JsonBean list(){
        List<Employee> list = empService.findALlEmps();
        return new JsonBean(1, list);
    }
    @RequestMapping("/add.do")
    public JsonBean addEmp(Employee emp){
        empService.addEmp(emp);
        return new JsonBean(1, null);
    }

    @RequestMapping("/delete.do")
    public JsonBean delEmp(Integer id){
        empService.deleteEmpById(id);
        return new JsonBean(1, null);
    }
    @RequestMapping("/query.do")
    public JsonBean findEmpById(Integer id){
        Employee emp = empService.findEmpById(id);

        return new JsonBean(1,emp);
    }

    @RequestMapping("/update.do")
    public JsonBean updateEmp(Employee emp){
        empService.updateEmp(emp);
        return new JsonBean(1,null);
    }

    @RequestMapping("/page.do")
    public JsonBean findByPage(Integer page, Integer size){
        VPageInfo<Employee> pageInfo = empService.findByPage(page, size);
        return new JsonBean(1, pageInfo);
    }

}
