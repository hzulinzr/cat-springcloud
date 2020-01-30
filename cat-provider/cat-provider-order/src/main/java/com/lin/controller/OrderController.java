package com.lin.controller;

import com.lin.dto.OrderDTO;
import com.lin.dto.OrderListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.OrderService;
import com.lin.tools.Page;
import com.lin.vo.OrderListVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzr
 * @date 2020-01-16 10:32:02
 */
@Controller
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

    @PostMapping("/order/finish")
    public Wrapper<Void> orderFinish(){
        return null;
    }

    @PostMapping("/pay")
    @ResponseBody
    public String alipay(){
        return orderService.alipay();
    }

    @RequestMapping("/")
    public String toTest(){
        return "index";
    }
}
