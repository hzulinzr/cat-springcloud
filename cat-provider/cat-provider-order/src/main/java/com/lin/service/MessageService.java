package com.lin.service;

import com.lin.dto.MessageInsertDTO;
import com.lin.dto.MessageListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;

/**
 * @author lzr
 * @date 2020-03-20 20:21:32
 */
public interface MessageService {
    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    Wrapper<PageData<MessageListInfoVo>> messageList(MessageListDTO messageListDTO, int page, int rows);
    /**
     * 查看消息不同类型未读的数量
     * @param messageListDTO
     * @return 返回消息不通类型未读的数量
     */
    Integer messageUnreadCount(MessageListDTO messageListDTO);

    /**
     * 调整消息列表的消息状态
     * @param messageListDTO
     * @return 返回消息列表
     */
    Wrapper<PageData<MessageListInfoVo>> messageAdjust(MessageListDTO messageListDTO);
    /**
     * 添加消息
     * @param messageInsertDTO
     * @return
     */
    Wrapper<Void> messageInsert(MessageInsertDTO messageInsertDTO);
}
