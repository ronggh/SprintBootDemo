package cn.alan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.alan.mapper")
@SpringBootApplication
public class DataMybatisMain {
    public static void main(String[] args) {
        SpringApplication.run(DataMybatisMain.class, args);
    }
}
