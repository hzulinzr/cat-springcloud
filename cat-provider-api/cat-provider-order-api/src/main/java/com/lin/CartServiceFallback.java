package com.lin;

import com.lin.feign.CartServiceFeign;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.CartVo;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2020-01-13 16:34:04
 */
@Component
public class CartServiceFallback implements CartServiceFeign {
    @Override
    public Wrapper<PageData<CartVo>> cartList(Long userId) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }
}
