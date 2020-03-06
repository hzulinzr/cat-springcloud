package com.lin.fallback;

import com.lin.dto.AliPayDTO;
import com.lin.dto.OrderDTO;
import com.lin.dto.OrderListDTO;
import com.lin.feign.OrderServiceFeign;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.OrderListVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 21:33:03
 */
@Component
public class OrderServiceFallback implements OrderServiceFeign {
    @Override
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, Integer page, Integer rows) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Void> orderAdd(OrderDTO orderDTO) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Void> orderFinish(AliPayDTO aliPayDTO) {
        return Wrapper.fail(ResponseCode.ORDER_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO) {
        return null;
    }
}
