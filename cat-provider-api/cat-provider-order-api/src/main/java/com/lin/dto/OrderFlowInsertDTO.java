package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-04 21:16:37
 */
@Data
public class OrderFlowInsertDTO {
    private Long userId;
    private Double amount;
    private Double payAmount;
    private Integer payMethod;
    private Long orderId;
}
