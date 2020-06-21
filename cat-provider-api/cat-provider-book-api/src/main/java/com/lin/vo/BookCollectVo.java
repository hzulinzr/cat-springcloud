package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-18 00:50:42
 */
@Data
public class BookCollectVo {
    /**
     * 书籍id
     */
    private Long bookId;
    /**
     * 书籍名称
     */
    private String bookName;
    /**
     * 收藏时间
     */
    private Long createTime;
    /**
     * 书籍图片
     */
    private String bookUrl;
    /**
     * 收藏id
     */
    private Long id;
}
