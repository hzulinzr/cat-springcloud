package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-21 23:00:44
 */
@Data
public class BookOrderDTO {
    /**
     * 书籍和订单表的id
     */
    private Long id;
    /**
     * 订单中的书籍状态(0:待评价，1：已评价）
     */
    private Integer state;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 书籍数量
     */
    private Integer quantity;
}
