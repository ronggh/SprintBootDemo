package cn.alan.controller;

import cn.alan.entity.Department;
import cn.alan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/search",method = RequestMethod.GET)
    public List<Department> searchByName(String deptName){
        return deptService.searchByName(deptName);
    }
}
