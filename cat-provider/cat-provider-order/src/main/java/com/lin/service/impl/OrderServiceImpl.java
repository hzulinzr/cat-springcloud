package com.lin.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lin.config.alipay.AlipayConfig;
import com.lin.dao.OrderMapper;
import com.lin.dto.OrderDTO;
import com.lin.dto.OrderListDTO;
import com.lin.model.Order;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.OrderListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lzr
 * @date 2020-01-16 10:39:58
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 查询订单列表
     *
     * @param orderListDTO 订单查询实体类
     * @param page         页码
     * @return 返回订单列表
     */
    @Override
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, Page page) {
        int count = orderMapper.searchOrderListCount(orderListDTO);
        List<OrderListVo> orderListVo = new ArrayList<>();
        if (0 < count) {
            orderListVo = orderMapper.searchOrderList(orderListDTO, page);
        }
        return Wrapper.success(orderListVo, count);
    }

    /**
     * 创建订单
     *
     * @param orderDTO 创建订单实体类
     * @return
     */
    @Override
    public Wrapper<Void> orderAdd(OrderDTO orderDTO) {
        long orderId = new SnowFlake(0, 0).nextId();
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(orderDTO.getUserId());
        order.setCreateTime(System.currentTimeMillis());
        //待支付
        order.setPayState(-1);
        //预订单
        order.setOrderState(0);
        //插入订单
        orderMapper.insert(order);
        //向MQ发布消息
        return Wrapper.success();
    }

    @Override
    public String alipay() {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        //订单号
        String outTradeNo = "2";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ 0.01 +"\","
                + "\"subject\":\""+ "东野圭吾" +"\","
                + "\"body\":\""+ "书籍买卖" +"\","
                + "\"timeout_express\":\""+ "1c" +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            log.info("异常错误", e);
        }
        return result;
    }
}
