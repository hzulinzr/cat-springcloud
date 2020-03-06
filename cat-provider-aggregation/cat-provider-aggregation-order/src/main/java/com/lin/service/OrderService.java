package com.lin.service;

import com.lin.dto.OrderDTO;
import com.lin.dto.OrderFinishDTO;
import com.lin.dto.OrderInsertDTO;
import com.lin.dto.OrderListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.BookInfoVo;
import com.lin.vo.OrderListVo;

import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 20:33:24
 */
public interface OrderService {

    /**
     * 查询订单列表
     * @param orderListDTO  订单查询实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回订单列表
     */
    Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, int page, int rows);

    /**
     * 创建订单 （支付宝扫码支付）
     * @param orderInsertDTO
     * @return
     */
    String aliPay(OrderInsertDTO orderInsertDTO);

    /**
     * 创建订单 （余额支付）
     * @param orderInsertDTO
     * @return
     */
    Wrapper<Void> orderBalanceInsert(OrderInsertDTO orderInsertDTO);

    /**
     * 完成订单
     * @param orderFinishDTO
     * @return
     */
    Wrapper<Void> orderFinish(OrderFinishDTO orderFinishDTO);
    /**
     * 查询订单详情列表
     * @param orderDTO
     * @return
     */
    Wrapper<List<BookInfoVo>> orderInfoList(OrderDTO orderDTO);


}
