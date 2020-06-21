package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏表：collect
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Collect extends BaseModel {

    /**
     * 表字段：collect.id 注释：收藏表id
     */
    private Long id;
    /**
     * 表字段：collect.book_id 注释：书籍id
     */
    private Long bookId;
    /**
     * 表字段：collect.user_id 注释：用户id
     */
    private Long userId;
}