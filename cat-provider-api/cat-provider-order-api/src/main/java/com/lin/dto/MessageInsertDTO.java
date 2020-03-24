package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-24 15:39:57
 */
@Data
public class MessageInsertDTO {
    /**
     * 评论内容
     */
    private String content;
    /**
     * 收到消息的用户id
     */
    private Long userId;
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 消息类型, 1：评论，2：审核， 3：通知
     */
    private Integer type;
    /**
     * 发消息的用户id
     */
    private Long uploadUserId;

}
