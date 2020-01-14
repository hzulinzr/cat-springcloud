package com.lin.service;

import com.lin.dto.RegisterDTO;
import com.lin.response.Wrapper;
import com.lin.vo.UserLoginSuccessVo;
import com.lin.vo.UserRegisterSuccessVo;

import java.io.IOException;

/**
 * @author lzr
 * @date 2020-01-11 15:23:25
 */
public interface AuthUserService {
    /**
     * 用户登录获取token
     * @param username 账号
     * @param password 密码
     * @return 返回token
     */
    Wrapper<UserLoginSuccessVo> login(String username, String password);

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    Wrapper<UserRegisterSuccessVo> register(RegisterDTO registerDTO);
}
