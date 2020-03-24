package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ：book_order
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class BookOrder extends BaseModel {

    /**
     * 表字段：book_order.id 注释：id
     */
    private Long id;
    /**
     * 表字段：book_order.order_id 注释：订单id
     */
    private Long orderId;
    /**
     * 表字段：book_order.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：book_order.quantity 注释：书籍数量
     */
    private Integer quantity;
    /**
     * 表字段：book_order.user_id 注释：用户id
     */
    private Long userId;
}