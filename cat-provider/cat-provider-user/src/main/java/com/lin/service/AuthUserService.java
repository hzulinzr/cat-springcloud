package com.lin.service;

import com.lin.dto.BalanceUpdateDTO;
import com.lin.dto.BaseAuthUser;
import com.lin.dto.RegisterDTO;
import com.lin.dto.UserListDTO;
import com.lin.model.AuthUser;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.UserListVo;
import com.lin.vo.UserLoginSuccessVo;
import com.lin.vo.UserRegisterSuccessVo;

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

    /**
     * 用户账号余额转账
     * @param balanceUpdateDTO
     * @return
     */
    Wrapper<Void> balanceUpdate(BalanceUpdateDTO balanceUpdateDTO);

    /**
     * 查看用户详情信息
     * @param baseAuthUser
     * @return 返回用户详情
     */
    Wrapper<AuthUser> userInfo(BaseAuthUser baseAuthUser);

    /**
     * 用户列表
     * @param userListDTO
     * @param page 页码
     * @return
     */
    Wrapper<PageData<UserListVo>> userList(UserListDTO userListDTO, Page page);
}
