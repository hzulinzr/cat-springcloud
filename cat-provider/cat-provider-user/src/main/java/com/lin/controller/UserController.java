package com.lin.controller;

import com.lin.dto.UserDTO;
import com.lin.model.AuthClient;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;
import com.lin.service.UserService;
import com.lin.service.impl.AuthClientServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author lzr
 * @date 2019/11/11 11:53 下午
 */
@RestController
public class UserController {

    private final UserService userService;
    private final AuthClientServiceImpl authClientService;

    public UserController(UserService userService, AuthClientServiceImpl authClientService) {
        this.userService = userService;
        this.authClientService = authClientService;
    }

    /**
     * 登录生成token
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/user/login")
    public Wrapper<User> login(String username, String password){
        return userService.login(username, password);
    }

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @PostMapping("/user/register")
    public Wrapper<User> register(@RequestBody @Validated({UserDTO.Install.class}) UserDTO userDTO) {
        return userService.register(userDTO);
    }

    /**
     * 注销
     * @param userDTO
     * @return
     */
    @PostMapping("/user/logout")
    public Wrapper<User> logout(@RequestBody @Validated({UserDTO.Logout.class}) UserDTO userDTO){
        return userService.logout(userDTO);
    }

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    @PostMapping("/user/info/update")
    public Wrapper<UserInfo> userInfoUpdate(@RequestBody UserInfo userInfo){
        return userService.userInfoUpdate(userInfo);
    }

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

}
