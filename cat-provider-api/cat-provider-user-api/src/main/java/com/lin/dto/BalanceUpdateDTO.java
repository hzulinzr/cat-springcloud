package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-02-22 17:44:09
 */
@Data
public class BalanceUpdateDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 加减类型（ 1：加， -1：减）
     */
    private Integer type;

}
