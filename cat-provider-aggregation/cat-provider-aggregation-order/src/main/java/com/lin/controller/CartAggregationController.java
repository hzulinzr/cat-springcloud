package com.lin.controller;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartAggregationService;
import com.lin.vo.CartAdjustVo;
import com.lin.vo.CartListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author lzr
 * @date 2020-01-13 16:38:18
 */
@RestController
public class CartAggregationController {

    private CartAggregationService cartAggregationService;

    public CartAggregationController(CartAggregationService cartAggregationService) {
        this.cartAggregationService = cartAggregationService;
    }

    /**
     * 获取购物车列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @GetMapping("/cart/list")
    public Wrapper<PageData<CartListVo>> cartList(@NotNull(message = "userId not null") Long userId){
        return cartAggregationService.cartList(userId);
    }

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    @PostMapping("/cart/add")
    public Wrapper<Void> cartAdd(@RequestBody CartAddDTO cartAddDTO){
        return cartAggregationService.cartAdd(cartAddDTO);
    }

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    @PostMapping("/cart/adjust")
    public Wrapper<CartAdjustVo> cartAdjust(@RequestBody CartAdjustDTO cartAdjustDTO){
        return cartAggregationService.cartAdjust(cartAdjustDTO);
    }

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    @PostMapping("/cart/delete")
    public Wrapper<Void> cartDelete(@RequestBody CartDeleteDTO cartDeleteDTO){
        return cartAggregationService.cartDelete(cartDeleteDTO);
    }
}
