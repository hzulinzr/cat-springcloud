package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-14 14:28:39
 */
@Data
public class BookInfoVo {
    /**
     * 书籍id
     */
    private Long id;
    /**
     * 书籍类型名称
     */
    private String bookTypeName;
    /**
     * 书籍简介
     */
    private String bookContent;
    /**
     * 书籍名称
     */
    private String bookName;
    /**
     * 书籍作者
     */
    private String bookAuthor;
    /**
     * 书籍价格
     */
    private Double amount;
    /**
     * 上传的时间
     */
    private String uploadTime;
    /**
     * 上传的用户
     */
    private Long uploadUserId;
    /**
     * 下架时间
     */
    private String removeTime;
    /**
     * 书籍库存数量
     */
    private Integer bookQuantity;
    /**
     * 书籍地址
     */
    private String bookUrl;
    /**
     * 状态 （0：无效 1：有效）
     */
    private Integer state;
    /**
     * 购买的书籍数量
     */
    private Integer quantity;
}
