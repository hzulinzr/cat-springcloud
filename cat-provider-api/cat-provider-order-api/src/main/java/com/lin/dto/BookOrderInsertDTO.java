package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-21 15:21:58
 */
@Data
public class BookOrderInsertDTO {
    /**
     * 书籍和订单表的id
     */
    private Long id;
    /**
     * 订单中的书籍状态(0:待评价，1：已评价）
     */
    private Integer state;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 书籍列表
     */
    private List<BookListDTO> bookList;
    /**
     * 用户id
     */
    private Long userId;

}
