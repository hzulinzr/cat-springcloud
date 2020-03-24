package com.lin.controller;

import com.lin.dto.*;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderAggregationService;
import com.lin.vo.BookInfoVo;
import com.lin.vo.OrderAllListVo;
import com.lin.vo.OrderListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 20:32:56
 */
@RestController
public class OrderAggregationController {
    private OrderAggregationService orderAggregationService;

    public OrderAggregationController(OrderAggregationService orderAggregationService) {
        this.orderAggregationService = orderAggregationService;
    }

    /**
     * 查询订单列表
     * @param orderListDTO  订单查询实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回订单列表
     */
    @GetMapping("/order/list")
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO,  int page,  int rows){
        return orderAggregationService.orderList(orderListDTO, page, rows);
    }

    /**
     * 创建订单 （支付宝扫码支付）
     * @param orderInsertDTO
     * @return
     */
    @PostMapping("/order/insert")
    public String aliPay(@RequestBody OrderInsertDTO orderInsertDTO){
        return orderAggregationService.aliPay(orderInsertDTO);
    }

    /**
     * 创建订单 （余额支付）
     * @param orderInsertDTO
     * @return
     */
    @PostMapping("/order/balance/insert")
    public Wrapper<Void> orderBalanceInsert(@RequestBody OrderInsertDTO orderInsertDTO){
        return orderAggregationService.orderBalanceInsert(orderInsertDTO);
    }

    /**
     * 完成订单
     * @param orderFinishDTO
     * @return
     */
    @PostMapping("/order/finish")
    public Wrapper<Void> orderFinish(@RequestBody OrderFinishDTO orderFinishDTO){
        return orderAggregationService.orderFinish(orderFinishDTO);
    }

    /**
     * 查询订单详情列表
     * @param orderDTO
     * @return
     */
    @GetMapping("/order/info/list")
    public Wrapper<List<BookInfoVo>> orderInfoList(OrderDTO orderDTO){
        return orderAggregationService.orderInfoList(orderDTO);
    }

    /**
     * 查看用户的订单列表（包含详情）
     * @param orderAllListDTO
     * @param rows 行数
     * @param page 页码
     * @return 返回用户的订单列表 （包含详情）
     */
    @GetMapping("/order/all/list")
    public Wrapper<PageData<OrderAllListVo>> orderAllList(OrderAllListDTO orderAllListDTO, int page,  int rows){
        return orderAggregationService.orderAllList(orderAllListDTO, page, rows);
    }


}
