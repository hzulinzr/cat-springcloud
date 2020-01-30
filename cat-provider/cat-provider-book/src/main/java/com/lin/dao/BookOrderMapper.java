package com.lin.dao;

import com.lin.model.BookOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 数据操作接口
 * @author hzh 2020-01-19
*/
@Mapper
@Repository
public interface BookOrderMapper extends BaseMapper<BookOrder, Long> {
}