package com.lin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzr
 * @date 2019-12-07 15:36:01
 */
@Data
public class BookUpdateDTO extends BaseBookDTO{

    @ApiModelProperty(value = "书籍名称")
    private String bookName;

    @ApiModelProperty(value = "书籍作者")
    private String bookAuthor;

    @ApiModelProperty(value = "书籍价格")
    private Double amount;

    @ApiModelProperty(value = "书籍数量")
    private Integer bookQuantity;

    @ApiModelProperty(value = "书籍类型名称")
    private String bookTypeName;
}
