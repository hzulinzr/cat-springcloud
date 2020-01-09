package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息：user_info
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseModel {

    /**
     * 表字段：user_info.id 注释：用户信息id
     */
    private Long id;
    /**
     * 表字段：user_info.phone 注释：手机号码
     */
    private String phone;
    /**
     * 表字段：user_info.sex 注释：用户性别
     */
    private String sex;
    /**
     * 表字段：user_info.age 注释：用户年龄
     */
    private Integer age;
    /**
     * 表字段：user_info.name 注释：用户姓名
     */
    private String name;
    /**
     * 表字段：user_info.address 注释：用户居住地址
     */
    private String address;
}