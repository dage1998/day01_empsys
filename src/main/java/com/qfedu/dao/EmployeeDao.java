package com.qfedu.dao;

import com.qfedu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {

    //  查询所有员工数据
    public List<Employee> findAll();

    // 添加
    public void add(Employee emp);

    // 删除
    public void deleteById(Integer id);

    // 根据员工id查询员工数据
    public Employee findById(Integer id);

    // 更新
    public void update(Employee emp);

    // 分页  index 表示开始索引，size表示每页显示的记录个数
    // 如果传递多个参数，可以使用@Param修饰，内部会将数据封装到map结构中，key和@Param的参数保持一致
    public List<Employee> findByIndexAndSize(@Param("index") Integer index, @Param("size") Integer size);

    // 获取总记录数
    public int count();

}
