package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单：order
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseModel {

    /**
     * 表字段：order.id 注释：订单id
     */
    private Long id;
    /**
     * 表字段：order.user_id 注释：用户id
     */
    private Long userId;
    /**
     * 表字段：order.order_no 注释：订单编号
     */
    private String orderNo;
    /**
     * 表字段：order.amount 注释：订单金额
     */
    private Double amount;
    /**
     * 表字段：order.pay_time 注释：订单支付时间
     */
    private Long payTime;
    /**
     * 表字段：order.pay_amount 注释：支付金额
     */
    private Double payAmount;
    /**
     * 表字段：order.contact_person 注释：联系人
     */
    private String contactPerson;
    /**
     * 表字段：order.contact_address 注释：联系人地址
     */
    private String contactAddress;
    /**
     * 表字段：order.contact_phone 注释：联系人电话
     */
    private String contactPhone;
    /**
     * 表字段：order.order_state 注释：订单状态（-1：失败，0：预订单，1：已完成）
     */
    private Integer orderState;
    /**
     * 表字段：order.pay_state 注释：支付状态（-1：待支付，1：已支付）
     */
    private Integer payState;
    /**
     * 表字段：order.pay_method 注释：支付方式（0：余额，1：支付宝）
     */
    private Integer payMethod;
}