package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论：comment
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseModel {

    /**
     * 表字段：comment.id 注释：评论id
     */
    private Long id;
    /**
     * 表字段：comment.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：comment.content 注释：评论内容
     */
    private String content;
    /**
     * 表字段：comment.user_id 注释：评论id
     */
    private Long userId;
    /**
     * 表字段：comment.score 注释：评分
     */
    private Integer score;
}