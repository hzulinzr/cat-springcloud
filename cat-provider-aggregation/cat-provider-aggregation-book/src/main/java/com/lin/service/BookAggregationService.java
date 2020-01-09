package com.lin.service;

import com.lin.dto.BookListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.BookListVo;

/**
 * @author lzr
 * @date 2020-01-07 12:32:19
 */
public interface BookAggregationService {
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows);
}
