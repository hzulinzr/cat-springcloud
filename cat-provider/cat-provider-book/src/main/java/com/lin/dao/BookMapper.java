package com.lin.dao;

import com.lin.dto.BookListDTO;
import com.lin.model.Book;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import com.lin.vo.BookTypeCountVo;
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
    /**
     * 获取书籍类型数量
     * @return
     */
    List<BookTypeCountVo> bookTypeCount();
    int bookStateCount(@Param("state") Integer state);

    int bookOneDayCount(@Param("begin") Long begin, @Param("end") Long end);

    int newUserCount(@Param("begin") Long begin, @Param("end") Long end);

    int allUserCount();
}