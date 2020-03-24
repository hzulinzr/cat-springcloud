package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-24 21:10:25
 */
@Data
public class BookTypeCountVo {
    /**
     * 书籍类型名称
     */
    private String bookTypeName;
    /**
     * 书籍类型数量
     */
    private Integer bookTypeNameCount;
}
