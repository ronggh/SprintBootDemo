package cn.alan.controller;

import cn.alan.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {
    /**
     * pom.xml中引入XML支持
     *         <!-- 支持XML数据返回格式 -->
     *         <dependency>
     *             <groupId>com.fasterxml.jackson.dataformat</groupId>
     *             <artifactId>jackson-dataformat-xml</artifactId>
     *         </dependency>
     *         即可返回XML格式数据
     * @return
     */
    @GetMapping("/person")
    @ResponseBody
    public Person getPerson(){
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(20);
        return person;
    }
}
