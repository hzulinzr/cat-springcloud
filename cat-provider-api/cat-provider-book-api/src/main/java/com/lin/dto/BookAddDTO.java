package com.lin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lzr
 * @date 2019-12-07 15:19:30
 */
@Data
public class BookAddDTO extends BaseBookDTO{

    @NotBlank(message = "bookName not null")
    @ApiModelProperty(value = "书籍名称")
    private String bookName;

    @NotBlank(message = "bookAuthor not null")
    @ApiModelProperty(value = "书籍作者")
    private String bookAuthor;

    @NotNull(message = "amount not null")
    @ApiModelProperty(value = "书籍价格")
    private Double amount;

    @NotNull(message = "bookQuantity not null")
    @ApiModelProperty(value = "书籍数量")
    private Integer bookQuantity;

    @NotBlank(message = "bookTypeName not null")
    @ApiModelProperty(value = "书籍类型名称")
    private String bookTypeName;

    @NotBlank(message = "bookUrl not null")
    @ApiModelProperty(value = "书籍图片地址")
    private String bookUrl;
    /**
     * 用户id
     */
    private Long uploadUserId;
}
