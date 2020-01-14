package com.lin.service.impl;

import com.lin.dao.UserInfoMapper;
import com.lin.dao.UserMapper;
import com.lin.dto.UserDTO;
import com.lin.jwt.JwtUtil;
import com.lin.model.User;
import com.lin.model.UserInfo;
import com.lin.response.Wrapper;
import com.lin.service.UserService;
import com.lin.tools.MD5Util;
import com.lin.tools.ShortUUID;
import com.lin.tools.SnowFlake;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;


/**
 * @author lzr
 * @date 2019/11/12 12:10 上午
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;

    private final StringRedisTemplate stringRedisTemplate;

    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, StringRedisTemplate stringRedisTemplate) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 注销
     * @param userDTO
     * @return
     */
    @Override
    public Wrapper<User> logout(UserDTO userDTO) {
        User user = userMapper.findByUuid(userDTO.getUuid());
        if(null == user){
            return Wrapper.fail("user not null");
        }
        user.setIsLogin(0);
        user.setLastLoginTime(System.currentTimeMillis());

        //之后可以自己写一个update语句，减少不必要的时间消耗
        userMapper.update(user);
        return Wrapper.success(user);
    }

    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    @Override
    public Wrapper<UserInfo> userInfoUpdate(UserInfo userInfo) {
        UserInfo userInfoNew = userInfoMapper.findById(userInfo.getId());
        if(null == userInfoNew){
            return Wrapper.fail("userInfo not null");
        }
        userInfoNew.setAddress(userInfo.getAddress());
        userInfoNew.setAge(userInfo.getAge());
        userInfoNew.setName(userInfo.getName());
        userInfoNew.setPhone(userInfo.getPhone());
        userInfoNew.setSex(userInfo.getSex());
        userInfoMapper.update(userInfoNew);
        return Wrapper.success();
    }
}
