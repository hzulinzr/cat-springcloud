package com.lin.controller;

import com.lin.dto.BaseBookDTO;
import com.lin.dto.BookListDTO;
import com.lin.model.Book;
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

    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    @GetMapping("/book/list")
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows) {
        return bookAggregationService.bookList(bookListDTO, page, rows);
    }

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    @GetMapping("/book/info")
    public Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO){
        return bookAggregationService.bookInfo(baseBookDTO);
    }

}
