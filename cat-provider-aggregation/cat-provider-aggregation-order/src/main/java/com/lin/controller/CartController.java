package com.lin.controller;

import com.lin.model.Cart;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.vo.CartListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author lzr
 * @date 2020-01-13 16:38:18
 */
@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 获取购物车列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @GetMapping("/cart/list")
    public Wrapper<PageData<CartListVo>> cartList(@NotNull(message = "userId not null") Long userId){
        return cartService.cartList(userId);
    }

}
