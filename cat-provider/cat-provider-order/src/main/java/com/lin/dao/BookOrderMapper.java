package com.lin.dao;

import com.lin.dto.BookOrderDTO;
import com.lin.dto.BookOrderUpdateDTO;
import com.lin.model.BookOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 数据操作接口
 * @author hzh 2020-03-01
*/
@Mapper
@Repository
public interface BookOrderMapper extends BaseMapper<BookOrder, Long> {
    /**
     * 插入书籍和订单表id
     * @param bookOrderDTO
     * @return
     */
    int orderBookInsert(@Param("'bookOrderInsertDTO'") BookOrderDTO bookOrderDTO);

    /**
     * 更新书籍订单关联表的数据
     * @param bookOrderUpdateDTO
     * @return
     */
    int orderBookUpdate(@Param("bookOrderUpdateDTO") BookOrderUpdateDTO bookOrderUpdateDTO);
}