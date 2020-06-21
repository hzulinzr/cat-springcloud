package com.lin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lzr
 * @date 2019-12-07 15:24:07
 */
@Data
public class BaseBookDTO {
    @NotNull(message = "bookId not null")
    @ApiModelProperty(value = "书籍id", required = true)
    private Long bookId;

}
