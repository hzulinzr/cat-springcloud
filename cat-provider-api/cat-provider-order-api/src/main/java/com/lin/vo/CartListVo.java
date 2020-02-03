package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-13 16:50:30
 */
@Data
public class CartListVo {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 书籍地址
     */
    private String bookUrl;
    /**
     * 书籍名称
     */
    private String bookName;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 购物车书籍数量
     */
    private Integer quantity;
    /**
     * 书籍单价
     */
    private Double amount;
    /**
     * 书籍库存数量
     */
    private Integer bookQuantity;
}
