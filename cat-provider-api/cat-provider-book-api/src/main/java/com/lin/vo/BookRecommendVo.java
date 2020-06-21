package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-19 19:00:30
 */
@Data
public class BookRecommendVo {
    private Long bookId;
    private String bookUrl;
    private String bookName;
    private String bookAuthor;
    private Double amount;
    private Integer thumbsUp;
}
