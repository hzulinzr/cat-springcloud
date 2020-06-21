package com.lin.service;

import com.lin.dto.ThumbsUpInfoDTO;
import com.lin.dto.ThumbsUpInsertDTO;
import com.lin.dto.ThumbsUpUpdateDTO;
import com.lin.model.ThumbsUp;
import com.lin.response.Wrapper;

/**
 * @author lzr
 * @date 2020-04-18 00:25:38
 */
public interface ThumbsUpService {
    /**
     * 获取用户对书籍的点赞详情
     * @param thumbsUpInfoDTO
     * @return 返回用户对书籍的点赞详情
     */
    Wrapper<ThumbsUp> thumbsUpInfo(ThumbsUpInfoDTO thumbsUpInfoDTO);
    /**
     * 更改用户的点赞状态
     * @param thumbsUpUpdateDTO
     * @return
     */
    Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO);
    /**
     * 插入用户对书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    Wrapper<Void> thumbsUpInsert(ThumbsUpUpdateDTO thumbsUpUpdateDTO);
}
