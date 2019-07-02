package com.qfedu.service.impl;

import com.qfedu.Utils.JsonUtils;
import com.qfedu.dao.EmployeeDao;
import com.qfedu.pojo.Employee;
import com.qfedu.redis.RedisOperation;
import com.qfedu.service.EmployeeService;
import com.qfedu.vo.VPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // required=false，不是必须写的
    @Autowired(required = false)
    private EmployeeDao empDao;

    @Autowired
    private RedisOperation redisOperation;

    @Override
    public void addEmp(Employee emp) {
        empDao.add(emp);
    }

    @Override
    public List<Employee> findALlEmps() {
        return empDao.findAll();
    }

    @Override
    public void deleteEmpById(Integer id) {

        empDao.deleteById(id);
    }

    @Override
    public Employee findEmpById(Integer id) {

        //先从缓存中查询,如果没有，从数据库查,查到后，将数据放入缓存；如果缓存中有,直接返回；

        //从哈希结构中获取值
        String empHash = redisOperation.getHash("empHash", "emp" + id);
        //声明一下这个对象
        Employee employee = null;
        //如果 没有查到 ，为空或者为空字符串
        if (empHash == null || empHash.isEmpty()){

            //从数据库进行查询
            employee = empDao.findById(id);
            //当从数据库中查询出来不为空的话
            if (employee != null){
                //那就将 查出来的对象转换成Json字符串
                String empJson1 = JsonUtils.obj2Json(employee);
                //将其存入redis中的Hash中
                redisOperation.setHash("empHash","emp"+id,empJson1);
            }
        }else{
            //如果从哈希结构中获取到值的话 ，将其转换为对象
             employee = JsonUtils.json2Obj(empHash, Employee.class);
        }

        return employee;
    }

    @Override
    public void updateEmp(Employee emp) {
        empDao.update(emp);
    }

    @Override
    public VPageInfo<Employee> findByPage(Integer page, Integer size) {

        VPageInfo<Employee> pageInfo = new VPageInfo<>();
        // 计算分页开始的索引
        int index = (page - 1) * size;
        List<Employee> list = empDao.findByIndexAndSize(index, size);
        // 设置当前页
        pageInfo.setCurrentPage(page);
        // 设置当前页的记录
        pageInfo.setPageList(list);
        // 计算总页数
        int totalPage = 0;
        int count = empDao.count();
        if(count % size == 0){
            totalPage = count / size;
        }else{
            totalPage = count / size + 1;
        }
        // 设置总页数
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }
}





