package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-05-04 20:19:17
 */
@Data
public class MessageVo {
    private Long userId;
    private String username;
    private String userUrl;
    private String message;
    /**
     * 消息类型：0：次要消息：在线人数 1：主要消息
     */
    private Integer type;
}
