package com.lin.dao;

import com.lin.dto.BalanceUpdateDTO;
import com.lin.dto.RegisterDTO;
import com.lin.dto.UserListDTO;
import com.lin.model.AuthUser;
import com.lin.tools.Page;
import com.lin.vo.UserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 用户账号余额转账
     * @param balanceUpdateDTO
     * @return
     */
    int balanceUpdate(@Param("balanceUpdateDTO") BalanceUpdateDTO balanceUpdateDTO);

    /**
     * 查找用户列表数量
     * @param userListDTO
     * @return 返回用户列表数量
     */
    int searchUserListCount(@Param("userListDTO") UserListDTO userListDTO);

    /**
     * 查找用户列表
     * @param userListDTO
     * @param page
     * @return 返回用户列表
     */
    List<UserListVo> searchUserList(@Param("userListDTO") UserListDTO userListDTO, @Param("page") Page page);

}