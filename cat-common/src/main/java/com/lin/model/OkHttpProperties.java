package com.lin.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lzr
 * @date 2020-01-11 16:00:23
 */
@Data
@ConfigurationProperties("okhttp")
public class OkHttpProperties {
    private Integer connectTimeout = 30;

    private Integer readTimeout = 30;

    private Integer writeTimeout = 30;

    private Integer maxIdleConnections = 200;

    private Long keepAliveDuration = 300L;
}
