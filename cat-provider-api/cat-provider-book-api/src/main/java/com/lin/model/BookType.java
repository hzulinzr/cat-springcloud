package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 书籍类型ID：book_type
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class BookType extends BaseModel {

    /**
     * 表字段：book_type.id 注释：书籍类型ID
     */
    private Long id;
    /**
     * 表字段：book_type.book_type_name 注释：书籍类型名称
     */
    private String bookTypeName;
    /**
     * 表字段：book_type.book_content 注释：书籍简介
     */
    private String bookContent;
}