package com.lin.dao;

import com.lin.model.AuthUser;
import com.lin.model.Resource;
import com.lin.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据操作接口
 * @author hzh 2019-11-25
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, Long> {

    /**
     * 通过uuid查询用户
     * @param uuid
     * @return 返回用户
     */
    User findByUuid(@Param("uuid") String uuid);
}