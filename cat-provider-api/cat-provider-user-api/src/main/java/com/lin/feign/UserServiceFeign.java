package com.lin.feign;

import com.lin.dto.UserDTO;
import com.lin.fallback.UserServiceFallback;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lzr
 * @date 2019-12-03 11:31:57
 */
@FeignClient(name = "cat-provider-user", fallback = UserServiceFallback.class)
public interface UserServiceFeign {

    /**
     * 登录生成token
     * @param username
     * @param password
     * @return
     */
    @GetMapping("user/login")
    Wrapper<User> login(@RequestParam String username, @RequestParam String password);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @PostMapping("user/register")
    Wrapper<User> register(@RequestBody UserDTO userDTO);

    /**
     * 注销
     * @param userDTO
     * @return
     */
    @PostMapping("user/logout")
    Wrapper<User> logout(@RequestBody UserDTO userDTO);

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    @PostMapping("user/info/update")
    Wrapper<UserInfo> userInfoUpdate(@RequestBody UserInfo userInfo);
}
