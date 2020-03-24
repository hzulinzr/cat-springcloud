package com.lin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-20 20:24:38
 */
@Data
public class MessageListVo {
    /**
     * 消息列表
     */
    private List<MessageListInfoVo> messageList;
    /**
     * 系统通知未读数量
     */
    private Integer systemUnreadCount;
    /**
     * 评论未读数量
     */
    private Integer commentUnreadCount;
    /**
     * 书籍审核未读数量
     */
    private Integer reviewUnreadCount;
}
