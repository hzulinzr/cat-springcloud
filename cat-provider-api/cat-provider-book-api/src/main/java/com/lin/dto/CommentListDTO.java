package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-04 19:48:51
 */
@Data
public class CommentListDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    private Long userId;
}
