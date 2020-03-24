package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-20 20:27:11
 */
@Data
public class MessageListDTO {
    /**
     * 消息类型, 1：评论，2：审核， 3：通知
     */
    private Integer type;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 消息的状态， 1：已读，0：未读
     */
    private Integer state;
    /**
     * 消息id列表
     */
    private List<Long> messageIds;

}
