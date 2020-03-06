package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-04 20:01:24
 */
@Data
public class CommentInsetDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 评分
     */
    private Integer score;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 评论内容
     */
    private String content;

}
