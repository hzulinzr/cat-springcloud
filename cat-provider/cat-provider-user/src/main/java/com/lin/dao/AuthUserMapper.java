package com.lin.dao;

import com.lin.dto.BaseUserDTO;
import com.lin.dto.RegisterDTO;
import com.lin.model.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户表ID数据操作接口
 * @author hzh 2020-01-11
*/
@Mapper
@Repository
public interface AuthUserMapper extends BaseMapper<AuthUser, Long> {

    /**
     * 获取用户信息
     * @param clientId
     * @return
     */
    AuthUser getUser(@Param("clientId") String clientId);

    /**
     * 用户登录
     * @param username 用户账号
     * @param password 密码
     * @return 返回用户信息
     */
    AuthUser login(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    int register(@Param("registerDTO") RegisterDTO registerDTO);
}