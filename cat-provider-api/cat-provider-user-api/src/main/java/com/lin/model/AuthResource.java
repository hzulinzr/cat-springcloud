package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限与客户端关联表ID：auth_resource
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthResource extends BaseModel {

    /**
     * 表字段：auth_resource.id 注释：权限与客户端关联表ID
     */
    private Long id;
    /**
     * 表字段：auth_resource.client_id 注释：用户id
     */
    private String clientId;
    /**
     * 表字段：auth_resource.resource_id 注释：权限表ID
     */
    private Long resourceId;
}