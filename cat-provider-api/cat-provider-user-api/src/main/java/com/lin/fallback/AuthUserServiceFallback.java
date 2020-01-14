package com.lin.fallback;

import com.lin.feign.AuthUserServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2020-01-11 15:27:57
 */
@Component
public class AuthUserServiceFallback implements AuthUserServiceFeign {
}
