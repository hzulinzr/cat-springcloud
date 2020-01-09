package com.lin.dao;

import com.lin.config.security.AuthDetails;
import com.lin.model.User;
import com.lin.response.Wrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据操作接口
 * @author hzh 2019-11-25
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, Long> {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * 通过uuid查询用户
     * @param uuid
     * @return 返回用户
     */
    User findByUuid(@Param("uuid") String uuid);

    AuthDetails getUser(String username);
}