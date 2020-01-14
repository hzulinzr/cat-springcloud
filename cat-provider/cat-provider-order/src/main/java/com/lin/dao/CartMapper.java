package com.lin.dao;

import com.lin.model.Cart;
import com.lin.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车表数据操作接口
 * @author hzh 2020-01-13
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart, Long> {
    List<CartVo> getCartList(Long userId);

    int getCartListCount(Long userId);
}