package com.lin.feign;

import com.lin.fallback.AuthUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author lzr
 * @date 2020-01-11 15:27:35
 */
@FeignClient(name = "cat-provider-user", fallback = AuthUserServiceFallback.class)
public interface AuthUserServiceFeign {
}
