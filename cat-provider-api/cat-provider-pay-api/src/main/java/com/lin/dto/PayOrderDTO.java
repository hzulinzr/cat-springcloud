package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-30 17:15:50
 */
@Data
public class PayOrderDTO {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 书籍名称
     */
    private String bookName;
    /**
     * 书籍描述
     */
    private String content;
}
