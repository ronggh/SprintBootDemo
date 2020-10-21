package cn.alan.controller;

import cn.alan.entity.User;
import cn.alan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

    @GetMapping("/user")
    public User insertUser(User user){
        return userService.insertUser(user);
    }

//    @GetMapping("/user/search")
//    public List<User> searchByName(String name){
//        return userService.searchByName(name);
//    }
}
