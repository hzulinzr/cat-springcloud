package com.lin.dao;

import com.lin.model.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资源表ID数据操作接口
 * @author hzh 2020-01-11
*/
@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<Resource, Long> {
    /**
     * 获取用户资源
     * @param clientId 用户账号
     * @return 返回用户资源列表
     */
    List<Resource>  getResource(@Param("clientId") String clientId);
}