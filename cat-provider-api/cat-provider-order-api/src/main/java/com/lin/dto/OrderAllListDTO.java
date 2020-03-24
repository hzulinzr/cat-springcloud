package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-20 12:56:08
 */
@Data
public class OrderAllListDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单中的书籍状态(0:待评价，1：已评价）
     */
    private Integer state;
}
