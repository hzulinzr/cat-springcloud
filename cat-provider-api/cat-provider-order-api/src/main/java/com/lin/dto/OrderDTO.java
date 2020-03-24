package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-20 15:07:03
 */
@Data
public class OrderDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 订单金额
     */
    private Double totalAmount;
}
