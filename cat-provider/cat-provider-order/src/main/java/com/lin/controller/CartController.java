package com.lin.controller;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-01-13 15:50:17
 */
@Slf4j
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
    public Wrapper<PageData<CartVo>> cartList(Long userId){
        return cartService.cartList(userId);
    }

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    @PostMapping("/cart/add")
    public Wrapper<Void> cartAdd(@RequestBody CartAddDTO cartAddDTO){
        return cartService.cartAdd(cartAddDTO);
    }

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    @PostMapping("/cart/adjust")
    public Wrapper<Void> cartAdjust(@RequestBody CartAdjustDTO cartAdjustDTO){
        return cartService.cartAdjust(cartAdjustDTO);
    }

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    @PostMapping("cart/delete")
    public Wrapper<Void> cartDelete(@RequestBody CartDeleteDTO cartDeleteDTO){
        return cartService.cartDelete(cartDeleteDTO);
    }
}
