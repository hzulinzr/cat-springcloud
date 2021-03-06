package com.lin.service.impl;

import com.lin.dao.CartMapper;
import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.model.Cart;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.tools.SnowFlake;
import com.lin.vo.CartAdjustVo;
import com.lin.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-13 16:11:20
 */
@Slf4j
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

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> cartAdd(CartAddDTO cartAddDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartAddDTO, cart);
        Cart cartInfo = cartMapper.searchCartInfo(cartAddDTO);
        //购物车是否存在该书籍
        if(null != cartInfo){
            //存在则书籍加1
            cartInfo.setQuantity(cartAddDTO.getQuantity());
            cartInfo.setTotalAmount(cartAddDTO.getTotalAmount());
            cartMapper.updateCart(cartInfo);
            log.info("购物书籍加1");
        }else{
            //在购物车新增一条数据
            Cart cartNew = new Cart();
            cartNew.setId(new SnowFlake(0, 0).nextId());
            cartNew.setBookId(cartAddDTO.getBookId());
            cartNew.setQuantity(cartAddDTO.getQuantity());
            cartNew.setTotalAmount(cartAddDTO.getTotalAmount());
            cartNew.setUserId(cartAddDTO.getUserId());
            cartMapper.insertCart(cartNew);
            log.info("{} 将书籍加入购物成功", cartAddDTO.getUserId());
        }
        return Wrapper.success();
    }

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<CartAdjustVo> cartAdjust(CartAdjustDTO cartAdjustDTO) {
        CartAddDTO cartAddDTO = new CartAddDTO();
        cartAddDTO.setBookId(cartAdjustDTO.getBookId());
        cartAddDTO.setUserId(cartAdjustDTO.getUserId());
        //根据用户id和书籍id获取购物车书籍详情
        Cart cart = cartMapper.searchCartInfo(cartAddDTO);
        //获取前端返回的书籍数量
        int newQuantity = cartAdjustDTO.getQuantity();
        double totalAmount = 0;
        if(0 != newQuantity) {
            //计算书籍总价钱
            totalAmount = (cart.getTotalAmount() / cart.getQuantity()) * (newQuantity);
        }
        cartAdjustDTO.setTotalAmount(totalAmount);
        cartMapper.adjustCart(cartAdjustDTO);
        CartAdjustVo cartAdjustVo = new CartAdjustVo();
        cartAdjustVo.setQuantity(newQuantity);
        cartAdjustVo.setTotalAmount(totalAmount);
        return Wrapper.success(cartAdjustVo);
    }

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> cartDelete(CartDeleteDTO cartDeleteDTO) {
        List<Long> bookIds = cartDeleteDTO.getBookIds();
        bookIds.forEach(bookId -> {
            cartDeleteDTO.setBookId(bookId);
            cartMapper.cartDelete(cartDeleteDTO);
        });
        log.info("用户：{} 删除购物车成功！", cartDeleteDTO.getUserId());
        return Wrapper.success();
    }
}
