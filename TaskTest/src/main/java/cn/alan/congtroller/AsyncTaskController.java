package cn.alan.congtroller;

import cn.alan.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncTaskController {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @GetMapping("/hello")
    public String hello(){
        asyncTaskService.hello();
        return "success";
    }
}
