package cn.alan.service;

import cn.alan.entity.Department;
import cn.alan.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService {
    @Resource
    private DeptMapper deptMapper;

    public List<Department> searchByName(String deptName) {
        return deptMapper.searchByName("%" + deptName + "%");
    }
}
