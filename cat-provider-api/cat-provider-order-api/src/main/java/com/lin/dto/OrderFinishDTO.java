package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-18 18:14:18
 */
@Data
public class OrderFinishDTO {
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 书籍列表
     */
    List<BookListDTO> bookList;
    /**
     * 用户id
     */
    private Long userId;
}
