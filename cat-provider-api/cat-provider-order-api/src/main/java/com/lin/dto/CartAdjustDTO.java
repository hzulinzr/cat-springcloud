package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-14 22:59:17
 */
@Data
public class CartAdjustDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 书籍数量
     */
    private Integer quantity;
}
