package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-04-18 01:46:43
 */
@Data
public class BookCollectUpdateDTO {
    /**
     * 收藏id
     */
    private Long id;
    /**
     * 收藏状态（1：已收藏， 0：未收藏）
     */
    private Integer state;
    private Long bookId;
    private Long userId;
}
