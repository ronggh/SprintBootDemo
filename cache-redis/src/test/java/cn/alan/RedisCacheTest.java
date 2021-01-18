package cn.alan;


import cn.alan.entity.Employee;
import cn.alan.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisCacheTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate;  //k-v都是对象的

    @Autowired
    private  RedisTemplate<Object, Employee> employeeRedisTemplate;

    /**
     * 1. 测试使用Redis保存数据
     */
    @Test
    public void testRedisSaveData() {
        // 保存字符串数据
        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        // 保存列表数据
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    /**
     * 2. 测试保存对象
     * 1）必须是可序列化的类
     * 2）默认使用jdk序列化机制,以二进制形式保存到redis中
     */
    @Test
    public void testSaveObject() {
        Employee emp = employeeMapper.getEmpById(2);
        redisTemplate.opsForValue().set("emp-2", emp);
    }

    /**
     * 3. 测试保存对象，以JSON方式
     * 如果相将数据以json的方式保存，可以通过创建 Redis的配置类，修改默认的序列化器来实现
     */
    @Test
    public void testSaveObject2Json() {
        Employee emp = employeeMapper.getEmpById(2);
        employeeRedisTemplate.opsForValue().set("emp-2", emp);
    }

}
