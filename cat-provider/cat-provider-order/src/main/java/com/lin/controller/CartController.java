package com.lin.controller;

import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.vo.CartVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-01-13 15:50:17
 */
@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 获取购物出列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @GetMapping("/cart/list")
    public Wrapper<PageData<CartVo>> cartList(Long userId){
        return cartService.cartList(userId);
    }
}
