package com.lin.service;

import com.lin.dto.UserDTO;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author lzr
 * @date 2019/11/11 11:57 下午
 */

public interface UserService {
    /**
     * 登录生成token
     * @param username
     * @param password
     * @return
     */
    Wrapper<User> login(String username, String password);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    Wrapper<User> register(UserDTO userDTO);

    /**
     * 注销
     * @param userDTO
     * @return
     */
    Wrapper<User> logout(UserDTO userDTO);

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    Wrapper<UserInfo> userInfoUpdate(UserInfo userInfo);
}
