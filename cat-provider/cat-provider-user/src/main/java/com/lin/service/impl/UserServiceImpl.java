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

    @Override
    public Wrapper<User> login(String username, String password) {
        User user = userMapper.login(username, password);
        if(null == user){
            return Wrapper.fail("user not null");
        }
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
            // 生成token
            String token = JwtUtil.generateToken(username);
            // 把token放入Redis中
            stringRedisTemplate.opsForValue().set(user.getUuid(), token);
            stringRedisTemplate.expire(token, JwtUtil.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            user.setToken(token);
            user.setIsLogin(1);
            user.setLastLoginTime(System.currentTimeMillis());
            userMapper.update(user);
            return Wrapper.success(user);
        }else{
            return Wrapper.fail("username or password error");
        }
    }

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @Override
    public Wrapper<User> register(UserDTO userDTO) {
//        //必须是6-10位字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）且不能以数字开头
//        String nameCheck = "^[^0-9][\\w_]{5,9}$";
//
//        //必须是6-20位的字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）
//        String passwordCheck = "^[\\w_]{6,20}$";
//
//        if(!userDTO.getUsername().matches(nameCheck)){
//            return Wrapper.fail("用户名格式不对");
//        }
//        if(!userDTO.getPassword().matches(passwordCheck)){
//            return Wrapper.fail("密码格式不对");
//        }
        String uuid = ShortUUID.randomShortUUID().replace("-", "");
        User user = new User();
        Long userInfoId = new SnowFlake(0 , 0).nextId();
        user.setId(new SnowFlake(0, 0).nextId());
        user.setUuid(uuid);
        user.setUserInfoId(userInfoId);
        user.setUsername(userDTO.getUsername());
        user.setPassword(user.getPassword());
        user.setRegisterTime(System.currentTimeMillis());
        userMapper.insert(user);

        //用户个信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getUserInfoId());
        userInfoMapper.insert(userInfo);
        return Wrapper.success();
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
