package com.lin.service;

import com.alipay.api.AlipayApiException;
import com.lin.dto.*;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.OrderListVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-16 10:39:44
 */
public interface OrderService {
    /**
     * 查询订单列表
     * @param orderListDTO  订单查询实体类
     * @param page 页码
     * @return 返回订单列表
     */
    Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, Page page);

    /**
     * 创建订单
     * @param orderDTO 创建订单实体类
     * @return
     */
    Wrapper<Void> orderAdd(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param aliPayDTO
     * @return
     */
    Wrapper<Void> orderFinish(AliPayDTO aliPayDTO);

    /**
     * 查询书籍id列表
     * @param orderDTO
     * @return 返回书籍id列表
     */
    Wrapper<List<Long>> orderBookIds(OrderDTO orderDTO);

    /**
     * 调整订单状态
     * @param orderStateDTO
     * @return
     */
    Wrapper<Void> orderStateAdjust(OrderStateDTO orderStateDTO);
    /**
     * 插入书籍和订单表数据
     * @param bookOrderInsertDTO
     * @return 返回书籍和订单表数据
     */
    Wrapper<Void> orderBookInsert(BookOrderInsertDTO bookOrderInsertDTO);
    /**
     * 更新书籍订单关联表的数据
     * @param bookOrderUpdateDTO
     * @return
     */
    Wrapper<Void> orderBookUpdate(BookOrderUpdateDTO bookOrderUpdateDTO);
}
