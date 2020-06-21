package com.lin.dto;

import com.lin.model.PageModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzr
 * @date 2019-12-07 14:57:30
 */
@Data
public class BookListDTO{
    @ApiModelProperty(value = "关键字检索")
    private String keyword;
    /**
     * 书籍类型
     */
    private String bookType;
    /**
     * 书籍状态
     */
    private Integer state;
    /**
     * 上传用户
     */
    private Long userId;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 上传用户id
     */
    private Long username;
}
