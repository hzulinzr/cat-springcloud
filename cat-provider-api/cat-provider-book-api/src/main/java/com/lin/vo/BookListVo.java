package com.lin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzr
 * @date 2019-12-05 23:46:16
 */
@Data
public class BookListVo {

    @ApiModelProperty("书籍id")
    private Long id;

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍作者")
    private String bookAuthor;

    @ApiModelProperty("书籍价格")
    private Double amount;

    @ApiModelProperty("书籍数量")
    private Integer bookQuantity;

    @ApiModelProperty("书籍类型名称")
    private String bookTypeName;

    @ApiModelProperty("书籍图片地址")
    private String bookUrl;

    /**
     * 上传用户
     */
    private Long uploadUserId;
    /**
     * 书籍状态
     */
    private Integer state;
    /**
     * 上传时间
     */
    private Long uploadTime;
}
