package com.lin.controller;

import com.lin.dto.OrderDTO;
import com.lin.dto.OrderFinishDTO;
import com.lin.dto.OrderInsertDTO;
import com.lin.dto.OrderListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderService;
import com.lin.tools.Page;
import com.lin.vo.BookInfoVo;
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
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询订单列表
     * @param orderListDTO  订单查询实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回订单列表
     */
    @GetMapping("/order/list")
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO,  Integer page,  Integer rows){
        return orderService.orderList(orderListDTO, page, rows);
    }

    /**
     * 创建订单 （支付宝扫码支付）
     * @param orderInsertDTO
     * @return
     */
    @PostMapping("/order/insert")
    public String aliPay(@RequestBody OrderInsertDTO orderInsertDTO){
        return orderService.aliPay(orderInsertDTO);
    }

    /**
     * 创建订单 （余额支付）
     * @param orderInsertDTO
     * @return
     */
    @PostMapping("/order/balance/insert")
    public Wrapper<Void> orderBalanceInsert(@RequestBody OrderInsertDTO orderInsertDTO){
        return orderService.orderBalanceInsert(orderInsertDTO);
    }


    /**
     * 完成订单
     * @param orderFinishDTO
     * @return
     */
    @PostMapping("/order/finish")
    public Wrapper<Void> orderFinish(@RequestBody OrderFinishDTO orderFinishDTO){
        return orderService.orderFinish(orderFinishDTO);
    }

    /**
     * 查询订单详情列表
     * @param orderDTO
     * @return
     */
    @GetMapping("/order/info/list")
    public Wrapper<List<BookInfoVo>> orderInfoList(OrderDTO orderDTO){
        return orderService.orderInfoList(orderDTO);
    }


}
