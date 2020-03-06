package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-02-09 17:51:25
 */
@Data
public class CartAdjustVo {
    /**
     * 书籍数量
     */
    private Integer quantity;
    /**
     * 书籍总价钱
     */
    private Double totalAmount;
}
