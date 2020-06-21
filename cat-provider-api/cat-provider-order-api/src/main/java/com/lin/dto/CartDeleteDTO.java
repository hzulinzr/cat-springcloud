package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-15 23:10:23
 */
@Data
public class CartDeleteDTO {
    /**
     * 书籍id列表
     */
    private List<Long> bookIds;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 书籍id
     */
    private Long bookId;
}
