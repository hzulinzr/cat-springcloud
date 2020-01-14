package com.lin.service.impl;

import com.lin.feign.BookServiceFeign;
import com.lin.feign.CartServiceFeign;
import com.lin.model.Cart;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.service.CartService;
import com.lin.vo.BookInfoVo;
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
public class CartServiceImpl implements CartService {

    private CartServiceFeign cartServiceFeign;
    private BookServiceFeign bookServiceFeign;

    public CartServiceImpl(CartServiceFeign cartServiceFeign, BookServiceFeign bookServiceFeign) {
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
        CartListVo cartListVo = new CartListVo();
        //查找数据库获取购物车列表
        Wrapper<PageData<CartVo>> cartListVoWrapper = cartServiceFeign.cartList(userId);
        if(ResponseCode.SUCCESS.getCode() != cartListVoWrapper.getCode()){
            return Wrapper.fail(cartListVoWrapper.getMessage());
        }
        PageData<CartVo> cartPageData = cartListVoWrapper.getData();
        if(null != cartPageData){
            //获取购物车列表数据
            List<CartVo> cartList =  cartPageData.getData();
            BeanUtils.copyProperties(cartList, cartListVo);
            //获取书籍id集合
            List<String> ids = cartList.stream().map(CartVo::getBookId).collect(Collectors.toList());
            //将list转换成以逗号隔开的字符串
            String bookIds = String.join(",", ids);
            //todo feign调用书籍详情接口
            Wrapper<List<BookInfoVo>> bookInfoListWrapper = bookServiceFeign.bookInfoList(bookIds);
            if(ResponseCode.SUCCESS.getCode() != bookInfoListWrapper.getCode()){
                return Wrapper.fail(bookInfoListWrapper.getMessage());
            }
            List<BookInfoVo> bookInfoVoList = bookInfoListWrapper.getData();
            if(null != bookInfoVoList){
                bookInfoVoList.forEach(bookInfo -> {
                    BeanUtils.copyProperties(bookInfo, cartListVo);
                });
            }
        }
        cartListVoList.add(cartListVo);

        return Wrapper.success(cartListVoList, cartListVoList.size());
    }
}
