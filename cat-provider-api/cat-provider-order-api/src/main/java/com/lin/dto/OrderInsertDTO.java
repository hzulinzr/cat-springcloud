package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 18:10:51
 */
@Data
public class OrderInsertDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 书籍列表
     */
    List<BookListDTO> bookList;
}
