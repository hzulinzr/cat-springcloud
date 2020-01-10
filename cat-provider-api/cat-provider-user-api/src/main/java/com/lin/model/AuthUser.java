package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公司表ID：company
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends BaseModel {

    /**
     * 表字段：company.id 注释：公司表ID
     */
    private Long id;
    /**
     * 表字段：company.update_time 注释：修改时间
     */
    private Long updateTime;
    /**
     * 表字段：company.name 注释：用户名字
     */
    private String name;
    /**
     * 表字段：company.addres 注释：公司地址
     */
    private String addres;
    /**
     * 表字段：company.details 注释：公司描述
     */
    private String details;
    /**
     * 表字段：company.tel 注释：联系电话号码
     */
    private String phoneNumber;
    /**
     * 资源id
     */
    private Long resourceId;
    private String clientId;
    private String secret;
}