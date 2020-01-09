package com.lin.dao;

import com.lin.model.Book;
import com.lin.vo.BookListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书籍表数据操作接口
 * @author lzr 2019-11-25
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<Book, Long> {
    int searchBookListCount(@Param("keyword") String keyword);

    List<BookListVo> searchBookList(@Param("keyword") String keyword, @Param("page") int page, @Param("rows") int rows);
}