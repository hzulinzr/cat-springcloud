package com.lin.service;

import com.lin.dto.*;
import com.lin.model.AuthUser;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 完善个人信息
     * @param authUserUpdateDTO
     * @return
     */
    Wrapper<Void> userUpdate(AuthUserUpdateDTO authUserUpdateDTO);

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    Wrapper<UserUrlVo> userUpload(MultipartFile file, Long userId);

    MessageVo messageInfo(String username);
}
