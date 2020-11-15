package cn.alan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * - @EnableAsync 开启异步功能
 * - @EnableScheduling 开启定时任务
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TaskTestMain {
    public static void main(String[] args) {
        SpringApplication.run(TaskTestMain.class,args);
    }
}
