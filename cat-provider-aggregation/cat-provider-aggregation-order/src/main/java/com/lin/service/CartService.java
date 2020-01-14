package com.lin.service;

import com.lin.model.Cart;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.CartListVo;


/**
 * @author lzr
 * @date 2020-01-13 16:48:52
 */
public interface CartService {
    /**
     * 获取购物车列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    Wrapper<PageData<CartListVo>> cartList(Long userId);
}
