package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-22 22:35:17
 */
@Data
public class MessageAdjustDTO {
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
     * 页码
     */
    private Integer page;
    /**
     * 行数
     */
    private Integer rows;
}
