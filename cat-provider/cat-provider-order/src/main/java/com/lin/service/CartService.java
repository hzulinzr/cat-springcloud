package com.lin.service;

import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.CartVo;

/**
 * @author lzr
 * @date 2020-01-13 15:47:26
 */
public interface CartService {

    /**
     * 获取购物出列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    Wrapper<PageData<CartVo>> cartList(Long userId);
}
