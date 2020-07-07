package com.lin.service.impl;

import com.lin.dao.CollectMapper;
import com.lin.dto.BookCollectDTO;
import com.lin.dto.BookCollectUpdateDTO;
import com.lin.model.Collect;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CollectService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.BookCollectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-04-18 00:26:13
 */
@Slf4j
@Service
public class CollectServiceImpl implements CollectService {
    private CollectMapper collectMapper;

    public CollectServiceImpl(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }


    /**
     * 获取用户收藏列表
     * @param bookCollectDTO
     * @param page
     * @return 返回用户收藏列表
     */
    @Override
    public Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Page page) {
        //获取用户收藏列表
        int count = collectMapper.searchBookCollectListCount(bookCollectDTO);
        List<BookCollectVo> bookCollectVos = new ArrayList<>();
        if(0 < count){
            bookCollectVos = collectMapper.searchBookCollectList(bookCollectDTO, page.getPage(), page.getRows());
        }
        return Wrapper.success(bookCollectVos, count);
    }

    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO) {
        Collect collect = new Collect();
        collect.setId(bookCollectUpdateDTO.getId());
        collect.setState(bookCollectUpdateDTO.getState());
        //更改收藏状态
        collectMapper.update(collect);
        return Wrapper.success();
    }
    /**
     * 获取收藏详情
     * @param bookCollectUpdateDTO
     * @return 返回收藏详情
     */
    @Override
    public Wrapper<Collect> collectInfo(BookCollectUpdateDTO bookCollectUpdateDTO) {
        Collect collect = collectMapper.collectInfo(bookCollectUpdateDTO);
        return Wrapper.success(collect);
    }

    /**
     * 插入收藏信息
     * @param bookCollectUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> bookCollectInsert(BookCollectUpdateDTO bookCollectUpdateDTO) {
        Collect collect = new Collect();
        BeanUtils.copyProperties(bookCollectUpdateDTO, collect);
        collect.setCreateTime(System.currentTimeMillis());
        collect.setId(new SnowFlake(0, 0).nextId());
        collectMapper.insert(collect);
        log.info("插入收藏成功");
        return Wrapper.success();
    }
}
