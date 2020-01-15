package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-15 23:10:23
 */
@Data
public class CartDeleteDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 用户id
     */
    private Long userId;
}
