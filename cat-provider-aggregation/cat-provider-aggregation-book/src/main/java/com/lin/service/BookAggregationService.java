package com.lin.service;

import com.lin.dto.BaseBookDTO;
import com.lin.dto.BookListDTO;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.BookListVo;

/**
 * @author lzr
 * @date 2020-01-07 12:32:19
 */
public interface BookAggregationService {
    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows);

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO);
}
