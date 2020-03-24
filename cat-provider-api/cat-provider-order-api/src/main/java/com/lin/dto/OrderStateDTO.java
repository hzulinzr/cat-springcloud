package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-19 00:21:01
 */
@Data
public class OrderStateDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单状态
     */
    private Integer orderState;
}
