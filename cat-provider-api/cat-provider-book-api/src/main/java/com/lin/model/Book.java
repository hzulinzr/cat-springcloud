package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 书籍表：book
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseModel {

    /**
     * 表字段：book.id 注释：书籍表id
     */
    private Long id;
    /**
     * 表字段：book.book_type_id 注释：书籍的类型id
     */
    private Long bookTypeId;
    /**
     * 表字段：book.book_name 注释：书籍名称
     */
    private String bookName;
    /**
     * 表字段：book.book_author 注释：书籍作者
     */
    private String bookAuthor;
    /**
     * 表字段：book.amount 注释：书籍价格
     */
    private Double amount;
    /**
     * 表字段：book.upload_time 注释：上传的时间
     */
    private Long uploadTime;
    /**
     * 表字段：book.upload_user_id 注释：上传的用户
     */
    private Long uploadUserId;
    /**
     * 表字段：book.remove_time 注释：下架时间
     */
    private Long removeTime;
    /**
     * 表字段：book.book_quantity 注释：书籍库存数量
     */
    private Integer bookQuantity;
    /**
     * 表字段：book.book_url 注释：书籍地址
     */
    private String bookUrl;
    /**
     * 表字段：book.thumbs_up 注释：点赞
     */
    private Integer thumbsUp;
}