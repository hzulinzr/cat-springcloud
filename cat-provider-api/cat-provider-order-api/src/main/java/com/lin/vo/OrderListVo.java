package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-19 09:58:19
 */
@Data
public class OrderListVo {
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 订单金额
     */
    private Double amount;
    /**
     * 订单支付时间
     */
    private Long payTime;
    /**
     * 订单创建时间
     */
    private Long createTime;
    /**
     * 支付金额
     */
    private Double payAmount;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系人地址
     */
    private String contactAddress;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 订单状态（-1：失败，0：预订单，1：已完成，2待评价）
     */
    private Integer orderState;
    /**
     * 支付状态（-1：待支付，1：已支付）
     */
    private Integer payState;
    /**
     * 支付方式（0：余额，1：支付宝）
     */
    private Integer payMethod;
}
