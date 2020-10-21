package cn.alan.mapper;

import cn.alan.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<Department> {
    @Select("select * from department where departmentName like #{deptName}")
    List<Department> searchByName(@Param("deptName") String deptName);
}
