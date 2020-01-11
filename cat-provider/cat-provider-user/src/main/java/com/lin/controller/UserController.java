package com.lin.controller;

import com.lin.dto.UserDTO;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;
import com.lin.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2019/11/11 11:53 下午
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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


}
