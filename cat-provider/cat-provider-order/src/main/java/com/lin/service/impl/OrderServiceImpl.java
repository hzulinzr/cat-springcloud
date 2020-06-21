package com.lin.service.impl;

import com.lin.dao.BookOrderMapper;
import com.lin.dao.OrderFlowMapper;
import com.lin.dao.OrderMapper;
import com.lin.dto.*;
import com.lin.model.Order;
import com.lin.model.OrderFlow;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.OrderFlowListVo;
import com.lin.vo.OrderListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private OrderFlowMapper orderFlowMapper;
    private BookOrderMapper bookOrderMapper;

    public OrderServiceImpl(OrderMapper orderMapper, OrderFlowMapper orderFlowMapper, BookOrderMapper bookOrderMapper) {
        this.orderMapper = orderMapper;
        this.orderFlowMapper = orderFlowMapper;
        this.bookOrderMapper = bookOrderMapper;
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
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderAdd(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getOrderId());
        order.setUserId(orderDTO.getUserId());
        order.setCreateTime(System.currentTimeMillis());
        order.setContactPerson(orderDTO.getContactPerson());
        //支付方式
        order.setPayMethod(orderDTO.getPayMethod());
        //订单金额
        order.setAmount(orderDTO.getTotalAmount());
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
     *
     * @param aliPayDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderFinish(AliPayDTO aliPayDTO) {
        Order order = new Order();
        order.setId(aliPayDTO.getOrderId());
        //已支付
        order.setPayState(1);
        //已完成
        order.setOrderState(1);
        order.setPayTime(aliPayDTO.getPayTime());
        //更新订单
        orderMapper.update(order);
        return Wrapper.success();
    }

    /**
     * 查询书籍id列表
     *
     * @param orderDTO
     * @return 返回书籍id列表
     */
    @Override
    public Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO) {
        List<Long> bookIds = orderMapper.searchBookIds(orderDTO);
        return Wrapper.success(bookIds);
    }

    /**
     * 调整订单状态
     *
     * @param orderStateDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderStateAdjust(OrderStateDTO orderStateDTO) {
        Order order = new Order();
        order.setUserId(orderStateDTO.getUserId());
        //订单状态
        order.setOrderState(orderStateDTO.getOrderState());
        //更新订单
        orderMapper.update(order);
        return Wrapper.success();
    }

    /**
     * 插入书籍和订单表数据
     *
     * @param bookOrderInsertDTO
     * @return 返回书籍和订单表数据
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderBookInsert(BookOrderInsertDTO bookOrderInsertDTO) {
        List<BookListDTO> bookList = bookOrderInsertDTO.getBookList();

        bookList.forEach(bookListDTO -> {
            BookOrderDTO bookOrderDTO = new BookOrderDTO();
            BeanUtils.copyProperties(bookOrderInsertDTO, bookOrderDTO);
            bookOrderDTO.setId(new SnowFlake(0,0).nextId());
            bookOrderDTO.setBookId(bookListDTO.getBookId());
            bookOrderDTO.setQuantity(bookListDTO.getQuantity());
            bookOrderMapper.orderBookInsert(bookOrderDTO);
        });
        log.info("插入书籍与订单关联表数据成功！");

        return Wrapper.success();
    }

    /**
     * 更新书籍订单关联表的数据
     *
     * @param bookOrderUpdateDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> orderBookUpdate(BookOrderUpdateDTO bookOrderUpdateDTO) {
        bookOrderMapper.orderBookUpdate(bookOrderUpdateDTO);
        log.info("{} 已评价该书籍: {}", bookOrderUpdateDTO.getUserId(), bookOrderUpdateDTO.getBookId());
        return Wrapper.success();
    }

    /**
     * 查看账单列表
     *
     * @param orderFlowListDTO
     * @param page
     * @return 返回账单列表
     */
    @Override
    public Wrapper<PageData<OrderFlowListVo>> orderFlowList(OrderFlowListDTO orderFlowListDTO, Page page) {
        int count = orderFlowMapper.searchOrderFlowListCount(orderFlowListDTO);
        List<OrderFlowListVo> orderFlowListVos = new ArrayList<>();
        if (0 < count) {
            orderFlowListVos = orderFlowMapper.searchOrderFlowList(orderFlowListDTO, page.getPage(), page.getRows());
        }
        return Wrapper.success(orderFlowListVos, count);
    }

    @Override
    public Wrapper<Void> orderFlowInsert(OrderFlowInsertDTO orderFlowInsertDTO) {
        OrderFlow orderFlow = new OrderFlow();
        BeanUtils.copyProperties(orderFlowInsertDTO, orderFlow);
        orderFlow.setId(new SnowFlake(0, 0).nextId());
        orderFlow.setCreateTime(System.currentTimeMillis());
        orderFlowMapper.insert(orderFlow);
        log.info("记录账单成功");
        return Wrapper.success();
    }

}
