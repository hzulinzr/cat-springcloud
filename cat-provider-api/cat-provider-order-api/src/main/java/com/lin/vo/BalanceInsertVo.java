package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-04 17:04:03
 */
@Data
public class BalanceInsertVo {
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 购买总金额
     */
    private Double totalAmount;
    /**
     * 支付时间
     */
    private Long payTime;
}
