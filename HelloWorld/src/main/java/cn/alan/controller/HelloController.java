package cn.alan.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
// 以上两个可用下面的一个代替
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello, world!";
    }
}
