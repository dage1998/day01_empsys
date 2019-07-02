package com.qfedu.service;

import com.qfedu.pojo.Employee;
import com.qfedu.vo.VPageInfo;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findALlEmps();

    public void addEmp(Employee emp);

    public void deleteEmpById(Integer id);

    public Employee findEmpById(Integer id);

    public void updateEmp(Employee emp);

    public VPageInfo<Employee> findByPage(Integer page, Integer size);
}
