package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单流水：order_flow
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderFlow extends BaseModel {

    /**
     * 表字段：order_flow.id 注释：订单流水id
     */
    private Long id;
    /**
     * 表字段：order_flow.user_id 注释：用户id
     */
    private Long userId;
    /**
     * 表字段：order_flow.order_id 注释：订单id
     */
    private Long orderId;
    /**
     * 表字段：order_flow.amount 注释：订单金额
     */
    private Double amount;
    /**
     * 表字段：order_flow.pay_method 注释：支付方式（0：余额，1：支付宝）
     */
    private Integer payMethod;
    /**
     * 表字段：order_flow.pay_amount 注释：支付金额
     */
    private Double payAmount;
}