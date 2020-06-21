package com.lin.fallback;

import com.lin.dto.*;
import com.lin.feign.OrderServiceFeign;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.OrderFlowListVo;
import com.lin.vo.OrderListVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/order/bookIds")
    @Override
    public Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> orderStateAdjust(OrderStateDTO orderStateDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> orderBookInsert(BookOrderInsertDTO bookOrderInsertDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> orderBookUpdate(BookOrderUpdateDTO bookOrderUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<PageData<OrderFlowListVo>> orderFlowList(OrderFlowListDTO orderFlowListDTO, int page, int rows) {
        return null;
    }

    @Override
    public Wrapper<Void> orderFlowInsert(OrderFlowInsertDTO orderFlowInsertDTO) {
        return null;
    }
}
