package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-14 20:17:43
 */
@Data
public class CartAddDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 用户id
     */
    private Long userId;
}
