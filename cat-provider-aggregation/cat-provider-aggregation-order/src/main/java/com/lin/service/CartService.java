package com.lin.service;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.model.Cart;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.CartAdjustVo;
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

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    Wrapper<Void> cartAdd(CartAddDTO cartAddDTO);

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    Wrapper<CartAdjustVo> cartAdjust(CartAdjustDTO cartAdjustDTO);

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    Wrapper<Void> cartDelete(CartDeleteDTO cartDeleteDTO);
}
