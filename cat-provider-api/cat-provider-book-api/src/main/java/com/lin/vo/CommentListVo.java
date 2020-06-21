package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-04 19:44:48
 */
@Data
public class CommentListVo {
    /**
     * 评论id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Long createTime;
    /**
     * 评分
     */
    private Integer score;
    /**
     * 评论用户名
     */
    private String username;
    /**
     * 书籍名称
     */
    private String bookName;
    private String userUrl;

}
