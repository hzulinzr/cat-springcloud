package com.lin.controller;

import com.lin.dto.BookListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.BookListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lzr
 * @date 2020-01-06 19:29:56
 */
@RestController
public class BookAggregationController {
    private final BookAggregationService bookAggregationService;

    public BookAggregationController(BookAggregationService bookAggregationService) {
        this.bookAggregationService = bookAggregationService;
    }

    @GetMapping("/book/list")
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows) {
        return bookAggregationService.bookList(bookListDTO, page, rows);
    }

}
