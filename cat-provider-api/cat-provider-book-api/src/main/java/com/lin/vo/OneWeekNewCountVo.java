package com.lin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lzr
 * @date 2020-03-24 21:12:49
 */
@Data
public class OneWeekNewCountVo {
    /**
     * 新增用户数量或者新增上架数量
     */
    private String name;
    /**
     * 类型： pieChart：饼状图， line：条形图
     */
    private String type;
    /**
     * 对应的7天的数据
     */
    private List<Integer> data;
}
