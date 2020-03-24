package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-20 23:14:45
 */
@Data
public class MessageListInfoVo {
    /**
     * 消息id
     */
    private Long id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 消息状态
     */
    private Integer state;
}
