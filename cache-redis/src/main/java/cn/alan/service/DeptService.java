package cn.alan.service;

import cn.alan.entity.Department;
import cn.alan.mapper.DepartmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "dept")
public class DeptService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 缓存的数据能存入redis；
     * 第二次从缓存中查询就不能反序列化回来；
     * 可以通过注解方式或代码方式
     *
     * @param id
     * @return
     */
    @Cacheable
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    /**
     * 代码方式
     *
     * @param id
     * @return
     */
    // 使用缓存管理器得到缓存，进行api调用
    public Department getDeptById2(Integer id) {
        System.out.println("查询部门" + id);
        Department department = null ;
        // 先从缓存中查，
        Cache deptCache = redisCacheManager.getCache("dept");
        String key = "dept:"+id;

        department = deptCache.get(key,Department.class);
        System.out.println(department);
        // 如果没有，则查数据库，并将结果放到缓存中
        if (null == department) {
            System.out.println("没有命中缓存...从数据库中查询数据...");
            department = departmentMapper.getDeptById(id);
            deptCache.put(key, department);
        }
        return department;
    }
}
