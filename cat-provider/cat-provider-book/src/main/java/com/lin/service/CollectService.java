package com.lin.service;

import com.lin.dto.BookCollectDTO;
import com.lin.dto.BookCollectUpdateDTO;
import com.lin.model.Collect;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.BookCollectVo;

/**
 * @author lzr
 * @date 2020-04-18 00:25:26
 */
public interface CollectService {
    /**
     * 获取用户收藏列表
     * @param bookCollectDTO
     * @param page
     * @return 返回用户收藏列表
     */
    Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Page page);
    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO);
    /**
     * 获取收藏详情
     * @param bookCollectUpdateDTO
     * @return 返回收藏详情
     */
    Wrapper<Collect> collectInfo(BookCollectUpdateDTO bookCollectUpdateDTO);
    /**
     * 插入收藏信息
     * @param bookCollectUpdateDTO
     * @return
     */
    Wrapper<Void> bookCollectInsert( BookCollectUpdateDTO bookCollectUpdateDTO);
}
