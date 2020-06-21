package com.lin.controller;

import com.lin.dto.*;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderService;
import com.lin.tools.Page;
import com.lin.vo.OrderFlowListVo;
import com.lin.vo.OrderListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-16 10:32:02
 */
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询订单列表
     * @param orderListDTO  订单查询实体类
     * @param page 页码
     * @return 返回订单列表
     */
    @GetMapping("/order/list")
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, Page page){
        return orderService.orderList(orderListDTO, page);
    }

    /**
     * 创建订单
     * @param orderDTO 创建订单实体类
     * @return
     */
    @PostMapping("/order/add")
    public Wrapper<Void> orderAdd(@RequestBody OrderDTO orderDTO){
        return orderService.orderAdd(orderDTO);
    }

    /**
     * 完成订单
     * @param aliPayDTO
     * @return
     */
    @PostMapping("/order/finish")
    public Wrapper<Void> orderFinish(@RequestBody AliPayDTO aliPayDTO){
        return orderService.orderFinish(aliPayDTO);
    }

    /**
     * 查询书籍id列表
     * @param orderDTO
     * @return 返回书籍id列表
     */
    @GetMapping("/order/bookIds")
    public Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO){
        return orderService.orderBookIds(orderDTO);
    }

    /**
     * 调整订单状态
     * @param orderStateDTO
     * @return
     */
    @PostMapping("/order/state/adjust")
    public Wrapper<Void> orderStateAdjust(@RequestBody OrderStateDTO orderStateDTO){
        return orderService.orderStateAdjust(orderStateDTO);
    }

    /**
     * 插入书籍和订单表数据
     * @param bookOrderInsertDTO
     * @return 返回书籍和订单表数据
     */
    @PostMapping("/order/book/insert")
    public Wrapper<Void> orderBookInsert(@RequestBody BookOrderInsertDTO bookOrderInsertDTO){
        return orderService.orderBookInsert(bookOrderInsertDTO);
    }

    /**
     * 更新书籍订单关联表的数据
     * @param bookOrderUpdateDTO
     * @return
     */
    @PostMapping("/order/book/update")
    public Wrapper<Void> orderBookUpdate(@RequestBody BookOrderUpdateDTO bookOrderUpdateDTO){
        return orderService.orderBookUpdate(bookOrderUpdateDTO);
    }

    /**
     * 查看账单列表
     * @param orderFlowListDTO
     * @param page
     * @return 返回账单列表
     */
    @GetMapping("/order/flow/list")
    public Wrapper<PageData<OrderFlowListVo>> orderFlowList(OrderFlowListDTO orderFlowListDTO, Page page){
        return orderService. orderFlowList(orderFlowListDTO, page);
    }

    /**
     * 插入账单
     * @param orderFlowInsertDTO
     * @return
     */
    @PostMapping("/order/flow/insert")
    public Wrapper<Void> orderFlowInsert(@RequestBody OrderFlowInsertDTO orderFlowInsertDTO){
        return  orderService.orderFlowInsert(orderFlowInsertDTO);
    }
}
