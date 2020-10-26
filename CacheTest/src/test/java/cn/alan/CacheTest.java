package cn.alan;

import cn.alan.entity.Employee;
import cn.alan.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = {CacheTest.class})
@RunWith(SpringRunner.class)
public class CacheTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 1.
     */
    @Test
    public void testEmployeeMapper(){
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}
