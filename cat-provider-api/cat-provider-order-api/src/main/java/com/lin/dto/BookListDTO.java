package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-02-21 22:54:51
 */
@Data
public class BookListDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 总金额
     */
    private Double totalAmount;
    /**
     * 书籍数量
     */
    private Integer quantity;
}
