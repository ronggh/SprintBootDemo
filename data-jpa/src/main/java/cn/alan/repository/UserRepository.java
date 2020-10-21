package cn.alan.repository;

import cn.alan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 继承JpaRepository,就已经有了基本的数据库操作功能
 */
public interface UserRepository extends JpaRepository<User,Integer> {
//    @Query("select id,last_name,email from tbl_user where last_name like %:name%")
//    List<User> searchByName(@Param("name") String name);
}
