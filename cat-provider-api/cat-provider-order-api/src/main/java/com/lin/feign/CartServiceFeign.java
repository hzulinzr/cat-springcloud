package com.lin.feign;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.fallback.CartServiceFallback;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.CartAdjustVo;
import com.lin.vo.CartVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    Wrapper<PageData<CartVo>> cartList(@RequestParam Long userId);

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    @PostMapping("/cart/add")
    Wrapper<Void> cartAdd(CartAddDTO cartAddDTO);

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    @PostMapping("/cart/adjust")
    Wrapper<CartAdjustVo> cartAdjust(CartAdjustDTO cartAdjustDTO);

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    @PostMapping("/cart/delete")
    Wrapper<Void> cartDelete(CartDeleteDTO cartDeleteDTO);
}
