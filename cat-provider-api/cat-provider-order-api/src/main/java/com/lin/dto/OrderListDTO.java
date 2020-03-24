package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-19 10:03:43
 */
@Data
public class OrderListDTO {
    /**
     * 关键字 （订单编号）
     */
    private String keyword;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单状态
     */
    private Integer orderState;
    /**
     * 订单中的书籍状态(0:待评价，1：已评价）
     */
    private Integer state;

}
