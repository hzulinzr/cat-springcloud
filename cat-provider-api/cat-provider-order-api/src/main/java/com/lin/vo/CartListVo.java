package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-13 16:50:30
 */
@Data
public class CartListVo {
    private String bookUrl;
    private String bookName;
    private String bookTypeName;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 购物车书籍数量
     */
    private Integer quantity;
}
