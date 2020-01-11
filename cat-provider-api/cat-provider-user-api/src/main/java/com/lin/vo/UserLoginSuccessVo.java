package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-11 15:18:51
 */
@Data
public class UserLoginSuccessVo {
    private String accessToken;
    private String scope;
}
