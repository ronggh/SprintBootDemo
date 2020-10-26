package cn.alan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.alan.mapper")
@ComponentScan("cn.alan")
@EnableCaching
public class CacheTestMain {
    public static void main(String[] args) {
        SpringApplication.run(CacheTestMain.class,args);
    }
}
