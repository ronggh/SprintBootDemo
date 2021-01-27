package cn.alan.config.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/")
    public String login(){

       return  "";
    }
}
