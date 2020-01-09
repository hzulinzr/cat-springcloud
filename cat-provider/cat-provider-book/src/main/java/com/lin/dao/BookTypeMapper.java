package com.lin.dao;

import com.lin.model.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 书籍类型ID数据操作接口
 * @author hzh 2019-11-25
*/
@Mapper
@Repository
public interface BookTypeMapper extends BaseMapper<BookType, Long> {
}