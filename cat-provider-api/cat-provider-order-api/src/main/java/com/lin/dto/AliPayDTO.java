package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-02-15 20:54:38
 */
@Data
public class AliPayDTO {
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 总的金额
     */
    private Double totalAmount;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 上传书籍的用户id
     */
    private Long uploadUserId;
    /**
     * 支付时间
     */
    private Long payTime;

}
