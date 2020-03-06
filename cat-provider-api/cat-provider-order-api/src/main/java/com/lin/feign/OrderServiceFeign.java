package com.lin.feign;

import com.lin.dto.AliPayDTO;
import com.lin.dto.OrderDTO;
import com.lin.dto.OrderListDTO;
import com.lin.fallback.OrderServiceFallback;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.OrderListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 21:32:48
 */
@FeignClient(name = "cat-provider-order", fallback = OrderServiceFallback.class)
public interface OrderServiceFeign {

    /**
     * 查询订单列表
     * @param orderListDTO
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/order/list")
    Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, @RequestParam Integer page, @RequestParam Integer rows);
    /**
     * 创建订单
     * @param orderDTO 创建订单实体类
     * @return
     */
    @PostMapping("/order/add")
    Wrapper<Void> orderAdd(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param aliPayDTO
     * @return
     */
    @PostMapping("/order/finish")
    Wrapper<Void> orderFinish(AliPayDTO aliPayDTO);

    /**
     * 查询书籍id列表
     * @param orderDTO
     * @return 返回书籍id列表
     */
    @GetMapping("/order/bookIds")
    Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO);
}
