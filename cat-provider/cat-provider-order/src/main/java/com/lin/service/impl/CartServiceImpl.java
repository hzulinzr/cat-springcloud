package com.lin.service.impl;

import com.lin.dao.CartMapper;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.vo.CartVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-13 16:11:20
 */
@Service
public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;

    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    /**
     * 获取购物出列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @Override
    public Wrapper<PageData<CartVo>> cartList(Long userId) {
        int count = cartMapper.getCartListCount(userId);
        List<CartVo> cartList = new ArrayList<>();
        if(0 < count){
            cartList = cartMapper.getCartList(userId);
        }
        return Wrapper.success(cartList, count);
    }
}
