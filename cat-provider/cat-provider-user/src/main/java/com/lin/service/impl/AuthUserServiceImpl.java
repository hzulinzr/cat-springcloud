package com.lin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.config.MD5;
import com.lin.dao.AuthUserMapper;
import com.lin.dto.*;
import com.lin.model.AuthUser;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.AuthUserService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author lzr
 * @date 2020-01-11 15:23:39
 */
@Slf4j
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private AuthUserMapper authUserMapper;

    private OkHttpClient okHttpClient;

    public AuthUserServiceImpl(AuthUserMapper authUserMapper, OkHttpClient okHttpClient) {
        this.authUserMapper = authUserMapper;
        this.okHttpClient = okHttpClient;
    }

    /**
     * 用户登录获取token
     * @param username 账号
     * @param password 密码
     * @return 返回token
     */
    @Override
    public Wrapper<UserLoginSuccessVo> login(String username, String password){

        AuthUser authUser = authUserMapper.login(username, password);
        if(null == authUser){
            return Wrapper.fail("Invalid username or password");
        }

        //远程调用获取token接口
        String url = "http://localhost:8071/oauth/token";
        url = url + "?grant_type=client_credentials&scope=all&client_id=" + username + "&client_secret=" + password ;

        Response response = doGet(url);
        String result = Optional.ofNullable(response).map(Response::body).map(responseBody -> {
            try {
                return responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }).orElse("");
        JSONObject jsonObject = JSONObject.parseObject(result);
        //获取令牌
        String accessToken = jsonObject.getString("access_token");
        if(null == accessToken){
            return Wrapper.fail("获取token失败，请重新登录");
        }
        //获取权限范围
        String scope = jsonObject.getString("scope");
        //返回给前端数据
        UserLoginSuccessVo userLoginSuccessVo = new UserLoginSuccessVo();
        userLoginSuccessVo.setAccessToken(accessToken);
        userLoginSuccessVo.setScope(scope);
        userLoginSuccessVo.setUserUrl(authUser.getUserUrl());
        userLoginSuccessVo.setUsername(username);
        userLoginSuccessVo.setUserId(authUser.getId());

        log.info("用户：{} 登录成功, access_token 为: {}", username, accessToken);
        return Wrapper.success(userLoginSuccessVo);
    }

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @Override
    public Wrapper<UserRegisterSuccessVo> register(RegisterDTO registerDTO) {
        //查询数据库是否已经存在该账号
        AuthUser user = authUserMapper.getUser(registerDTO.getUsername());
        if(null != user){
            log.info("已经存在该用户名！");
            return Wrapper.fail(30001, "已经存在该用户名");
        }
        registerDTO.setCreateTime(System.currentTimeMillis());
        registerDTO.setId(new SnowFlake(0, 0).nextId());

        //md5加密
        MD5 md5 = new MD5();
        String password = md5.encryption(registerDTO.getPassword());
        registerDTO.setPassword(password);
        registerDTO.setState(1);
        registerDTO.setBalance(0.0);
        //注册用户
        authUserMapper.register(registerDTO);
        log.info("注册用户成功！");
        return Wrapper.success();
    }

    /**
     * 用户账号余额转账
     * @param balanceUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> balanceUpdate(BalanceUpdateDTO balanceUpdateDTO) {
        AuthUser authUser = authUserMapper.findById(balanceUpdateDTO.getUserId());
        balanceUpdateDTO.setTotalAmount(authUser.getBalance() + balanceUpdateDTO.getType() * balanceUpdateDTO.getTotalAmount());
        authUserMapper.balanceUpdate(balanceUpdateDTO);
        return Wrapper.success();
    }

    /**
     * 查看用户详情信息
     * @param baseAuthUser
     * @return 返回用户详情
     */
    @Override
    public Wrapper<AuthUser> userInfo(BaseAuthUser baseAuthUser) {
        AuthUser authUser = authUserMapper.findById(baseAuthUser.getUserId());
        return Wrapper.success(authUser);
    }

    /**
     * 用户列表
     * @param userListDTO
     * @param page 页码
     * @return
     */
    @Override
    public Wrapper<PageData<UserListVo>> userList(UserListDTO userListDTO, Page page) {
        int totalCount = authUserMapper.searchUserListCount(userListDTO);
        List<UserListVo> userListVoList = new ArrayList<>();
        if(0 < totalCount){
            userListVoList = authUserMapper.searchUserList(userListDTO, page);
        }
        return Wrapper.success(userListVoList, totalCount);
    }

    /**
     * 完善个人信息
     * @param authUserUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> userUpdate(AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUser = new AuthUser();
        authUser.setName(authUserUpdateDTO.getName());
        authUser.setId(authUserUpdateDTO.getUserId());
        BeanUtils.copyProperties(authUserUpdateDTO, authUser);
        //完善个人信息
        authUserMapper.update(authUser);
        return Wrapper.success();
    }

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    @Override
    public Wrapper<UserUrlVo> userUpload(MultipartFile file, Long userId) {
        if(null == file) {
            log.info("上传文件为空");
            return Wrapper.fail("上传文件为空");
        }
        // 重新生成唯一文件名，用于存储数据库
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName;
        String path = "/Users/lzr/study/images/" + newFileName;
        File filePath = new File(path);
        try {
            //上传到指定路径
            file.transferTo(filePath);
        }catch(Exception e){
            log.info("上传文件失败", e);
        }

        //nginx搭建图片服务器，通过/images/转发到/Users/lzr/study/images/
        String pathNew = "/images/";
        String userUrl = pathNew + newFileName;
        UserUrlVo userUrlVo = new UserUrlVo();
        userUrlVo.setUserUrl(userUrl);
        AuthUser authUser = new AuthUser();
        authUser.setId(userId);
        authUser.setUserUrl(userUrl);
        authUserMapper.update(authUser);
        log.info("上传头像成功");

        return Wrapper.success(userUrlVo);
    }

    @Override
    public MessageVo messageInfo(String username) {
        return authUserMapper.userInfo(username);
    }

    /**
     * okHttpClient路径请求
     * @param url
     * @return
     */
    private Response doGet(String url) {
        try {
            Request request = new Request.Builder().url(url).build();
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
