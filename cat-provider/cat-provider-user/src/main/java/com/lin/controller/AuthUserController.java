package com.lin.controller;

import com.lin.dto.*;
import com.lin.model.AuthClient;
import com.lin.model.AuthUser;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.AuthUserService;
import com.lin.config.security.AuthClientServiceImpl;
import com.lin.tools.Page;
import com.lin.vo.UserListVo;
import com.lin.vo.UserLoginSuccessVo;
import com.lin.vo.UserRegisterSuccessVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lzr
 * @date 2020-01-11 15:14:50
 */
@RestController
public class AuthUserController {

    private AuthClientServiceImpl authClientService;
    private AuthUserService authUserService;

    public AuthUserController(AuthClientServiceImpl authClientService, AuthUserService authUserService) {
        this.authClientService = authClientService;
        this.authUserService = authUserService;
    }

    /**
     * oauth提供的用户信息接口
     */
    @GetMapping("/user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * 根据token获取用户信息 权限等等信息
     */
    @ApiOperation(value = "根据token获取用户信息 权限等等信息")
    @GetMapping("/user/info")
    public AuthClient getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authClientService.getAuthClient(authentication);
    }

    /**
     * 用户登录获取token
     * @param username 账号
     * @param password 密码
     * @return 返回token
     */
    @GetMapping("/user/login")
    public Wrapper<UserLoginSuccessVo> login(String username, String password) {
        return authUserService.login(username, password);
    }

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/user/register")
    public Wrapper<UserRegisterSuccessVo> register(@RequestBody RegisterDTO registerDTO){
        return authUserService.register(registerDTO);
    }

    /**
     * 用户账号余额转账
     * @param balanceUpdateDTO
     * @return
     */
    @PostMapping("/user/balance/update")
    public Wrapper<Void> balanceUpdate(@RequestBody BalanceUpdateDTO balanceUpdateDTO){
        return authUserService.balanceUpdate(balanceUpdateDTO);
    }

    /**
     * 查看用户详情信息
     * @param baseAuthUser
     * @return 返回用户详情
     */
    @GetMapping("/user/person/info")
    public Wrapper<AuthUser> userInfo(BaseAuthUser baseAuthUser){
        return authUserService.userInfo(baseAuthUser);
    }

    /**
     * 用户列表
     * @param userListDTO
     * @param page 页码
     * @return
     */
    @GetMapping("/user/list")
    public Wrapper<PageData<UserListVo>> userList(UserListDTO userListDTO, Page page){
        return authUserService.userList(userListDTO, page);
    }

    /**
     * 完善个人信息
     * @param authUserUpdateDTO
     * @return
     */
    @PostMapping("/user/info/update")
    public Wrapper<Void> userUpdate(@RequestBody AuthUserUpdateDTO authUserUpdateDTO){
        return authUserService.userUpdate(authUserUpdateDTO);
    }
}
