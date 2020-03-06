package com.lin.dao;

import com.lin.dto.OrderDTO;
import com.lin.dto.OrderListDTO;
import com.lin.model.Order;
import com.lin.tools.Page;
import com.lin.vo.OrderListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单数据操作接口
 * @author hzh 2020-01-19
*/
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order, Long> {
    /**
     * 查询订单列表记录数
     * @param orderListDTO 订单列表查询实体类
     * @return 返回订单列表记录数
     */
    int searchOrderListCount(@Param("orderListDTO") OrderListDTO orderListDTO);

    /**
     * 查询订单列表
     * @param orderListDTO 订单列表查询实体类
     * @param page 页码
     * @return 返回订单列表
     */
    List<OrderListVo> searchOrderList(@Param("orderListDTO") OrderListDTO orderListDTO, @Param("page") Page page);

    /**
     * 查询书籍id列表
     * @param orderDTO
     * @return 返回书籍id列表
     */
    List<Long> searchBookIds(@Param("orderDTO") OrderDTO orderDTO);

}