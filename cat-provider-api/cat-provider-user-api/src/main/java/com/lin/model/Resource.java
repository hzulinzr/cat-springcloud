package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源表ID：resource
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends BaseModel {

    /**
     * 表字段：resource.id 注释：资源表ID
     */
    private Long id;
    /**
     * 表字段：resource.name 注释：资源名字
     */
    private String name;
    /**
     * 表字段：resource.url 注释：资源url
     */
    private String url;
    /**
     * 表字段：resource.details 注释：资源描述
     */
    private String details;
    /**
     * 表字段：resource.scope 注释：资源范围
     */
    private String scope;
}