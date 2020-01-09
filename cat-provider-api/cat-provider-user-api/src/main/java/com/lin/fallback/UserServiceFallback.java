package com.lin.fallback;

import com.lin.dto.UserDTO;
import com.lin.feign.UserServiceFeign;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2019-12-03 11:40:05
 */
@Component
public class UserServiceFallback implements UserServiceFeign {

    /**
     * 登录生成token
     * @param username
     * @param password
     * @return
     */
    @Override
    public Wrapper<User> login(String username, String password) {
        return Wrapper.success(new User());
    }

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @Override
    public Wrapper<User> register(UserDTO userDTO) {
        return Wrapper.success(new User());
    }

    /**
     * 注销
     * @param userDTO
     * @return
     */
    @Override
    public Wrapper<User> logout(UserDTO userDTO) {
        return Wrapper.success(new User());
    }

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    @Override
    public Wrapper<UserInfo> userInfoUpdate(UserInfo userInfo) {
        return Wrapper.success(new UserInfo());
    }
}
