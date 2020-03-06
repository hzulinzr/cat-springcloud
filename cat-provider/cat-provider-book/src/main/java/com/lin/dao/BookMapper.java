package com.lin.dao;

import com.lin.dto.BookListDTO;
import com.lin.model.Book;
import com.lin.vo.BookInfoVo;
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
    int searchBookListCount(@Param("bookListDTO") BookListDTO bookListDTO);

    List<BookListVo> searchBookList(@Param("bookListDTO") BookListDTO bookListDTO, @Param("page") int page, @Param("rows") int rows);

    List<BookInfoVo> searchBookInfoList(@Param("ids") List<Long> ids);
}