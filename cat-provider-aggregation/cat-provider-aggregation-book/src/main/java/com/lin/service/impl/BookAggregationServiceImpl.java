package com.lin.service.impl;

import com.lin.dto.*;
import com.lin.feign.BookServiceFeign;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
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

    public BookAggregationServiceImpl(BookServiceFeign bookServiceFeign) {
        this.bookServiceFeign = bookServiceFeign;
    }


    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows) {
        return bookServiceFeign.bookList(bookListDTO, page, rows);
    }

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    @Override
    public Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO) {
        return bookServiceFeign.bookInfo(baseBookDTO);
    }

    @Override
    public Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO) {
        return bookServiceFeign.bookStateAdjust(bookStateAdjustDTO);
    }

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍评论列表
     */
    @Override
    public Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Integer page, Integer rows) {
        return bookServiceFeign.commentList(commentListDTO, page, rows);
    }

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @Override
    public Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO) {
        return bookServiceFeign.commentInsert(commentInsetDTO);
    }
}
