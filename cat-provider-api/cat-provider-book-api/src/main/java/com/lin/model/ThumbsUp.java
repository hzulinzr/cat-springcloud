package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 点赞表：thumbs_up
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class ThumbsUp extends BaseModel {

    /**
     * 表字段：thumbs_up.id 注释：点赞表id
     */
    private Long id;
    /**
     * 表字段：thumbs_up.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：thumbs_up.user_id 注释：用户id
     */
    private Long userId;
}