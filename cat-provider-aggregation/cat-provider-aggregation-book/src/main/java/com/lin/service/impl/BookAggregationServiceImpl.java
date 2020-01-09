package com.lin.service.impl;

import com.lin.dto.BookListDTO;
import com.lin.feign.BookServiceFeign;
import com.lin.feign.UserServiceFeign;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.BookListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lzr
 * @date 2020-01-07 12:35:38
 */
@Slf4j
@Service
public class BookAggregationServiceImpl implements BookAggregationService {

    private final BookServiceFeign bookServiceFeign;
    private final UserServiceFeign userServiceFeign;

    public BookAggregationServiceImpl(BookServiceFeign bookServiceFeign, UserServiceFeign userServiceFeign) {
        this.bookServiceFeign = bookServiceFeign;
        this.userServiceFeign = userServiceFeign;
    }

    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows) {
        return bookServiceFeign.bookList(bookListDTO, page, rows);
    }
}
