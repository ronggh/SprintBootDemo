package cn.alan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String lastName;
    private String email;
    //性别 1男  0女
    private Integer gender;
    private Integer dId;
}
