package com.lin.dao;

import com.lin.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户信息数据操作接口
 * @author hzh 2019-11-26
*/
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo, Long> {
}