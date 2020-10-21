package cn.alan.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 使用JPA注解配置映射关系
 * - @Entity ,告诉JPA这是一个实体类（和数据表映射的类）
 * - @Table(name = "tbl_user"),来指定和哪个数据表对应;如果省略默认表名就是user
 * - @Data,这是lombok的注解，用来生成 getter/setter方法
 */
@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    //这是和数据表对应的一个列
    @Column(name = "last_name",length = 50)
    private String lastName;

    @Column //省略默认列名就是属性名
    private String email;
}
