package cn.alan;

import cn.alan.entity.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  Spring Boot 的单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigYamlTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Person person;

    /**
     01- 测试从配置文件 application.yml中加载 person 的属性值
     */
    @Test
    public void testLoadValues(){
        System.out.println(person);
    }

    /**
     * 02 - 测试 @ImportResource 导入Spring 的配置文件
     * 1、新建一个Bean类，Cat
     * 2、新建Spring 配置文件 beans.xml
     * 3、本类中引入 IoC容器环境
     *  @Autowired
     *     private ApplicationContext applicationContext;
     *  4、测试是否创建了Cat实例
     *  5、在主启动类中使用 @ImportResource(locations = {"classpath:beans.xml"}) 再进行测试
     */
    @Test
    public void testImportResource(){
        boolean hasCat = applicationContext.containsBean("cat");
        System.out.println("has Cat in Ioc? " + hasCat);
    }


}
