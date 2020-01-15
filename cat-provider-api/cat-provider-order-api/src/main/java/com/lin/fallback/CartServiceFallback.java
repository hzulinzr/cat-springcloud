package com.lin.fallback;

import com.lin.dto.CartAddDTO;
import com.lin.dto.CartAdjustDTO;
import com.lin.dto.CartDeleteDTO;
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

    @Override
    public Wrapper<Void> cartAdd(CartAddDTO cartAddDTO) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Void> cartAdjust(CartAdjustDTO cartAdjustDTO) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Void> cartDelete(CartDeleteDTO cartDeleteDTO) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }
}
