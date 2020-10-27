package cn.alan.controller;

import cn.alan.entity.Department;
import cn.alan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDeptById(id);
    }

    @GetMapping("/dept2/{id}")
    public Department getDept2(@PathVariable("id") Integer id){
        return deptService.getDeptById2(id);
    }
}
