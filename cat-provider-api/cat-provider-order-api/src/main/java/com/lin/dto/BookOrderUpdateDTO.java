package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-21 23:44:25
 */
@Data
public class BookOrderUpdateDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 书籍id
     */
    private Long bookId;

}
