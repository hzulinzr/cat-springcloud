package com.lin.service;

import com.lin.dto.MessageAdjustDTO;
import com.lin.dto.MessageListDTO;
import com.lin.dto.MessageUnreadDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.MessageListVo;

/**
 * @author lzr
 * @date 2020-03-20 22:27:48
 */
public interface MessageAggregationService {
    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    Wrapper<PageData<MessageListVo>> messageList(MessageListDTO messageListDTO, int page, int rows);
    /**
     * 查看消息未读数量
     * @param messageUnreadDTO
     * @return 返回消息未读数量
     */
    Wrapper<Integer> messageUnreadCount(MessageUnreadDTO messageUnreadDTO);
    /**
     * 消息调整
     * @param messageAdjustDTO
     * @return 返回消息列表
     */
    Wrapper<PageData<MessageListVo>> messageAdjust(MessageAdjustDTO messageAdjustDTO);
}
