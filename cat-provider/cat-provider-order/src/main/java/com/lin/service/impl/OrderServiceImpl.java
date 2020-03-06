package com.lin.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lin.config.alipay.AlipayConfig;
import com.lin.config.rocketmq.RocketProducer;
import com.lin.dao.OrderMapper;
import com.lin.dto.AliPayDTO;
import com.lin.dto.OrderDTO;
import com.lin.dto.OrderInsertDTO;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lzr
 * @date 2020-01-16 10:39:58
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private RocketProducer rocketProducer;
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
     * @param orderDTO 创建订单实体类
     * @return
     */
    @Override
    public Wrapper<Void> orderAdd(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getOrderId());
        order.setUserId(orderDTO.getUserId());
        order.setCreateTime(System.currentTimeMillis());
        //待支付
        order.setPayState(-1);
        //预订单
        order.setOrderState(0);
        //插入订单
        orderMapper.insert(order);
        return Wrapper.success();
    }

    /**
     * 完成订单
     * @param aliPayDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderFinish(AliPayDTO aliPayDTO) {
        Order order = new Order();
        order.setUserId(aliPayDTO.getUserId());
        //已支付
        order.setPayState(1);
        //已完成
        order.setOrderState(1);
        //更新订单
        orderMapper.update(order);
        return Wrapper.success();
    }

    /**
     * 查询书籍id列表
     * @param orderDTO
     * @return 返回书籍id列表
     */
    @Override
    public Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO) {
        List<Long> bookIds = orderMapper.searchBookIds(orderDTO);
        return Wrapper.success(bookIds);
    }

}
