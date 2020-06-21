package com.lin.dao;

import com.lin.dto.OrderFlowListDTO;
import com.lin.model.OrderFlow;
import com.lin.vo.OrderFlowListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单流水数据操作接口
 * @author hzh 2020-04-04
*/
@Mapper
@Repository
public interface OrderFlowMapper extends BaseMapper<OrderFlow, Long> {
    int searchOrderFlowListCount(@Param("orderFlowListDTO") OrderFlowListDTO orderFlowListDTO);
    List<OrderFlowListVo> searchOrderFlowList(@Param("orderFlowListDTO") OrderFlowListDTO orderFlowListDTO, @Param("page") int page,
                                              @Param("rows") int rows);
}