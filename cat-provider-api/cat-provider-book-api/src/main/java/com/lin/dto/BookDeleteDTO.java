package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-10 22:39:18
 */
@Data
public class BookDeleteDTO {
    /**
     * 书籍id列表
     */
    private List<Long> bookIds;
}
