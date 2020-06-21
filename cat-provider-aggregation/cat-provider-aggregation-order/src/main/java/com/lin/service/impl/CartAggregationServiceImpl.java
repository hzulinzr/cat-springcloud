package com.lin.service.impl;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
import com.lin.feign.BookServiceFeign;
import com.lin.feign.CartServiceFeign;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.service.CartAggregationService;
import com.lin.vo.BookInfoVo;
import com.lin.vo.CartAdjustVo;
import com.lin.vo.CartListVo;
import com.lin.vo.CartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzr
 * @date 2020-01-13 16:49:10
 */
@Service
public class CartAggregationServiceImpl implements CartAggregationService {

    private CartServiceFeign cartServiceFeign;
    private BookServiceFeign bookServiceFeign;

    public CartAggregationServiceImpl(CartServiceFeign cartServiceFeign, BookServiceFeign bookServiceFeign) {
        this.cartServiceFeign = cartServiceFeign;
        this.bookServiceFeign = bookServiceFeign;
    }

    /**
     * 获取购物车列表
     * @param userId 用户id
     * @return 返回购物车列表
     */
    @Override
    public Wrapper<PageData<CartListVo>> cartList(Long userId) {
        List<CartListVo> cartListVoList = new ArrayList<>();

        //查找数据库获取购物车列表
        Wrapper<PageData<CartVo>> cartListVoWrapper = cartServiceFeign.cartList(userId);
        if(ResponseCode.SUCCESS.getCode() != cartListVoWrapper.getCode()){
            return Wrapper.fail(cartListVoWrapper.getMessage());
        }
        PageData<CartVo> cartPageData = cartListVoWrapper.getData();
        if(null != cartPageData){
            //获取购物车列表数据
            List<CartVo> cartList =  cartPageData.getData();
            cartList.forEach(cartVo -> {
                CartListVo cartListVo = new CartListVo();
                BeanUtils.copyProperties(cartVo, cartListVo);
                cartListVoList.add(cartListVo);
            });

            //获取书籍id集合
            List<Long> ids = cartList.stream().map(CartVo::getBookId).collect(Collectors.toList());
            if(0 != ids.size()) {
                // 根据书籍id列表获取书籍详情列表
                Wrapper<List<BookInfoVo>> bookInfoListWrapper = bookServiceFeign.bookInfoList(ids);
                if (ResponseCode.SUCCESS.getCode() != bookInfoListWrapper.getCode()) {
                    return Wrapper.fail(bookInfoListWrapper.getMessage());
                }
                List<BookInfoVo> bookInfoVoList = bookInfoListWrapper.getData();
                if (null != bookInfoVoList) {
                    bookInfoVoList.forEach(bookInfo -> {
                        cartListVoList.forEach(cartListVo -> {
                            if (bookInfo.getId().equals(cartListVo.getBookId())) {
                                cartListVo.setBookName(bookInfo.getBookName());
                                cartListVo.setAmount(bookInfo.getAmount());
                                cartListVo.setBookQuantity(bookInfo.getBookQuantity());
                                cartListVo.setBookUrl(bookInfo.getBookUrl());
                            }
                        });
                    });
                }
            }
        }


        return Wrapper.success(cartListVoList, cartListVoList.size());
    }

    /**
     * 加入购物车
     * @param cartAddDTO 书籍id
     * @return
     */
    @Override
    public Wrapper<Void> cartAdd(CartAddDTO cartAddDTO) {
        return cartServiceFeign.cartAdd(cartAddDTO);
    }

    /**
     * 调整购物车书籍数量
     * @param cartAdjustDTO 书籍id、调整类型
     * @return
     */
    @Override
    public Wrapper<CartAdjustVo> cartAdjust(CartAdjustDTO cartAdjustDTO) {
        return cartServiceFeign.cartAdjust(cartAdjustDTO);
    }

    /**
     * 删除购物车书籍
     * @param cartDeleteDTO 用户id、书籍id
     * @return
     */
    @Override
    public Wrapper<Void> cartDelete(CartDeleteDTO cartDeleteDTO) {
        return cartServiceFeign.cartDelete(cartDeleteDTO);
    }
}
