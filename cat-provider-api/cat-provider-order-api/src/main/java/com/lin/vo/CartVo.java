package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-14 10:11:49
 */
@Data
public class CartVo {
    /**
     * 购物车表id
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
     * 总金额
     */
    private Double totalAmount;
    /**
     * 购物车书籍数量
     */
    private Integer quantity;
}
