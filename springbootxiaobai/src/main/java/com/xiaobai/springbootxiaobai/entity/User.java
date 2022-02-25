package com.xiaobai.springbootxiaobai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName("xiaobai_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    //@JsonIgnore 忽略
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

    //protected String orderByClause;
    /**
     * 利用 Lombok 插件提供的注解 @Data替代get set
     */

}
