package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-05 21:19:43
 */
@Data
public class BookStateAdjustDTO {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 书籍状态
     */
    private Integer state;
}
