package com.lin.service.impl;

import com.lin.dao.ThumbsUpMapper;
import com.lin.dto.ThumbsUpInfoDTO;
import com.lin.dto.ThumbsUpInsertDTO;
import com.lin.dto.ThumbsUpUpdateDTO;
import com.lin.model.ThumbsUp;
import com.lin.response.Wrapper;
import com.lin.service.ThumbsUpService;
import com.lin.tools.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lzr
 * @date 2020-04-18 00:25:51
 */
@Service
@Slf4j
public class ThumbsUpServiceImpl implements ThumbsUpService {
    private ThumbsUpMapper thumbsUpMapper;

    public ThumbsUpServiceImpl(ThumbsUpMapper thumbsUpMapper) {
        this.thumbsUpMapper = thumbsUpMapper;
    }

    /**
     * 获取用户对书籍的点赞详情
     * @param thumbsUpInfoDTO
     * @return 返回用户对书籍的点赞详情
     */
    @Override
    public Wrapper<ThumbsUp> thumbsUpInfo(ThumbsUpInfoDTO thumbsUpInfoDTO) {
        ThumbsUp thumbsUp = thumbsUpMapper.thumbsUpInfo(thumbsUpInfoDTO);
        log.info("获取用户点赞详情");
        return Wrapper.success(thumbsUp);
    }

    /**
     * 更改用户的点赞状态
     * @param thumbsUpUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO) {
        ThumbsUp thumbsUp = new ThumbsUp();
        BeanUtils.copyProperties(thumbsUpUpdateDTO, thumbsUp);
        thumbsUpMapper.update(thumbsUp);
        log.info("更改用户点赞装状态成功");
        return Wrapper.success();
    }

    /**
     * 插入用户对书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> thumbsUpInsert(ThumbsUpUpdateDTO thumbsUpUpdateDTO) {
        ThumbsUp thumbsUp = new ThumbsUp();
        BeanUtils.copyProperties(thumbsUpUpdateDTO, thumbsUp);
        thumbsUp.setId(new SnowFlake(0,0).nextId());
        thumbsUp.setCreateTime(System.currentTimeMillis());
        thumbsUpMapper.insert(thumbsUp);
        log.info("插入点赞信息成功");
        return Wrapper.success();
    }
}
