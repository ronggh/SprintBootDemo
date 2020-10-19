package cn.alan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// 引入 Spring 的配置文件
//@ImportResource(locations = {"classpath:beans.xml"})
public class ConfigYamlMain {
    public static void main(String[] args) {
        SpringApplication.run(ConfigYamlMain.class,args);
    }
}
