package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息表：message
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends BaseModel {

    /**
     * 表字段：message.id 注释：消息表id
     */
    private Long id;
    /**
     * 表字段：message.user_id 注释：用户id
     */
    private Long userId;
    /**
     * 表字段：message.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：message.type 注释：消息类型, 1：评论，2：审核， 3：通知
     */
    private Integer type;
    /**
     * 表字段：message.content 注释：消息内容
     */
    private String content;
    /**
     * 表字段：message.upload_user_id 注释：上传的用户
     */
    private Long uploadUserId;
}