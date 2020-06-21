package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-18 15:38:31
 */
@Data
public class ThumbsUpUpdateDTO {
    /**
     * 点赞id
     */
    private Long id;
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 点赞数
     */
    private Integer thumbsUp;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 点赞状态
     */
    private Integer state;
}
