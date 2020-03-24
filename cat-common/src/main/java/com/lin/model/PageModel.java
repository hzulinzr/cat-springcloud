package com.lin.model;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-13 20:35:41
 */
@Data
public class PageModel {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 行数
     */
    private Integer rows;
}
