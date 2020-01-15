package com.lin.dao;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.model.Cart;
import com.lin.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车表数据操作接口
 * @author hzh 2020-01-13
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart, Long> {

    /**
     * 获取购物车列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    List<CartVo> getCartList(Long userId);

    /**
     * 获取购物车列表数目
     * @param userId 用户id
     * @return 返回购物车列表数目
     */
    int getCartListCount(Long userId);

    /**
     * 获取购物车详情
     * @param cartAddDTO 用户id、书籍id
     * @return 返回购物车详情
     */
    Cart searchCartInfo(@Param("cartAddDTO") CartAddDTO cartAddDTO);

    /**
     * 更新购物车书籍数目
     * @param cart
     * @return
     */
    int updateCart(@Param("cart") Cart cart);

    /**
     * 插入购物车书籍
     * @param cart
     * @return
     */
    int insertCart(@Param("cart") Cart cart);

    /**
     * 调整购物车书籍数目
     * @param cartAdjustDTO
     * @return
     */
    int adjustCart(@Param("cartAdjustDTO") CartAdjustDTO cartAdjustDTO);

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO
     * @return
     */
    int cartDelete(@Param("cartDeleteDTO")CartDeleteDTO cartDeleteDTO);
}