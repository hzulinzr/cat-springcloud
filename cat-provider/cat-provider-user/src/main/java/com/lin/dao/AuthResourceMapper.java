package com.lin.dao;

import com.lin.model.AuthResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 权限与客户端关联表ID数据操作接口
 * @author hzh 2020-01-11
*/
@Mapper
@Repository
public interface AuthResourceMapper extends BaseMapper<AuthResource, Long> {
}