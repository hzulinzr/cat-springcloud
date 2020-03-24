package com.lin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-24 21:02:43
 */
@Data
public class HomeInfoVo {
    /**
     * 新增用户数
     */
    private Integer newUserCount;
    /**
     * 所有用户数
     */
    private Integer allUserCount;
    /**
     * 上架书籍数
     */
    private Integer bookOnShelvesCount;
    /**
     * 待审核书籍数
     */
    private Integer bookReviewCount;
    /**
     * 1周新增列表
     */
    private List<OneWeekNewCountVo> oneWeekNewList;
    /**
     * 书籍类型列表
     */
    private List<BookTypeCountVo> bookTypeList;
}
