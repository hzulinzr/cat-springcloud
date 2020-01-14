package com.lin.feign;

import com.lin.CartServiceFallback;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.CartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzr
 * @date 2020-01-13 16:32:35
 */
@FeignClient(name = "cat-provider-order", fallback = CartServiceFallback.class)
public interface CartServiceFeign {
    /**
     * 获取购物出列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @GetMapping("/cart/list")
    Wrapper<PageData<CartVo>> cartList(Long userId);
}
