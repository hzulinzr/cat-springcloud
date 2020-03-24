package com.lin.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-23 13:44:13
 */
@Data
public class CommentDeleteDTO {
    /**
     * 评论id列表
     */
    List<Long> ids;

}
