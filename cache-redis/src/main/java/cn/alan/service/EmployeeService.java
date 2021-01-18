package cn.alan.service;

import cn.alan.entity.Employee;
import cn.alan.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@CacheConfig(cacheNames = "emp" )
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 几个属性：
     * cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     * <p>
     * key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值
     * 编写SpEL； #id;参数id的值   #a0  #p0  #root.args[0]
     * getEmp[2]
     * <p>
     * keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     * key/keyGenerator：二选一使用;
     * keyGenerator = "myKeyGenerator",
     * <p>
     * cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     * <p>
     * condition：指定符合条件的情况下才缓存；
     * ,condition = "#id>0"
     * condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
     * <p>
     * unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     * unless = "#result == null"
     * unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
     * sync：是否使用异步模式
     *
     * @param id
     * @return
     */

    @Cacheable( unless = "#result == null")
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * 使用 @CachePut时，cacheNames 和key值要和查询方法中的一致，<br/>
     * 否则，数据结果查询时不会取到更新后的值（不同的cacheNames和key在缓存中认为是不同的值）
     *
     * @param employee
     * @return
     */
    @CachePut(key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 删除的方法
     * - @CacheEvict：缓存清除
     * key：指定要清除的数据
     * allEntries = true：指定清除这个缓存中所有的数据
     * beforeInvocation = false：缓存的清除是否在方法之前执行
     * 默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     * <p>
     * beforeInvocation = true：
     * 代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */
    @CacheEvict(beforeInvocation = true/* ,key = "#id"*/)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        employeeMapper.deleteEmpById(id);
//        int i = 10/0;
    }

    /**
     * 根据lastName查询
     * - @Caching 定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(value="emp",key = "#lastName")
            },
            put = {
                    @CachePut(value="emp",key = "#result.id"),
                    @CachePut(value="emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

}
