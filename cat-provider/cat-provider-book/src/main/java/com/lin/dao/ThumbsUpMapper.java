package com.lin.dao;

import com.lin.dto.ThumbsUpInfoDTO;
import com.lin.model.ThumbsUp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 点赞表数据操作接口
 * @author hzh 2020-04-18
*/
@Mapper
@Repository
public interface ThumbsUpMapper extends BaseMapper<ThumbsUp, Long> {
    ThumbsUp thumbsUpInfo(@Param("thumbsUpInfoDTO") ThumbsUpInfoDTO thumbsUpInfoDTO);
}