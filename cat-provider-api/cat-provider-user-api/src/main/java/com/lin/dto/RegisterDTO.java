package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-12 00:54:06
 */
@Data
public class RegisterDTO {
    private String username;
    private String password;
    private Long createTime;
    private Long id;
}
