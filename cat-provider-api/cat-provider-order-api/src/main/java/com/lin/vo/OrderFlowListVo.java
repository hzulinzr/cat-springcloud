package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-04 21:17:34
 */
@Data
public class OrderFlowListVo {
    /**
     * 账单id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 收入金额
     */
    private Double amount;
    /**
     * 支付金额
     */
    private Double payAmount;
    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 账单创建时间
     */
    private Long createTime;
}
