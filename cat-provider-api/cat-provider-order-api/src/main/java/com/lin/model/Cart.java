package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 购物车表：cart
 * @author hzh
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseModel {

    /**
     * 表字段：cart.id 注释：购物车表id
     */
    private Long id;
    /**
     * 表字段：cart.user_id 注释：用户id
     */
    private Long userId;
    /**
     * 表字段：cart.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：cart.total_amount 注释：总金额
     */
    private Double totalAmount;
    /**
     * 表字段：cart.quantity 注释：书籍数量
     */
    private Integer quantity;
}