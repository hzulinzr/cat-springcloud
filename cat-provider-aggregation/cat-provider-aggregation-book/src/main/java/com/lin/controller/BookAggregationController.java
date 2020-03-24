package com.lin.controller;

import com.lin.dto.*;
import com.lin.feign.AuthUserServiceFeign;
import com.lin.feign.OrderServiceFeign;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
import com.lin.vo.HomeInfoVo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lzr
 * @date 2020-01-06 19:29:56
 */
@RestController
public class BookAggregationController {
    private BookAggregationService bookAggregationService;

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
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO,  int page,  int rows) {
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

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    @PostMapping("/book/state/adjust")
    public Wrapper<Void> bookStateAdjust(@RequestBody BookStateAdjustDTO bookStateAdjustDTO){
        return bookAggregationService.bookStateAdjust(bookStateAdjustDTO);
    }

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍评论列表
     */
    @GetMapping("/comment/list")
    public Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Integer page, Integer rows){
        return bookAggregationService.commentList(commentListDTO, page, rows);
    }

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @PostMapping("/comment/insert")
    public Wrapper<Void> commentInsert(@RequestBody CommentInsetDTO commentInsetDTO){
        return bookAggregationService.commentInsert(commentInsetDTO);
    }

    /**
     * 删除评论
     * @param commentDeleteDTO
     * @return
     */
    @PostMapping("comment/delete")
    public Wrapper<Void> commentDelete(@RequestBody CommentDeleteDTO commentDeleteDTO){
        return bookAggregationService.commentDelete(commentDeleteDTO);
    }

    /**
     * 管理后台首页的接口
     * @return
     */
    @GetMapping("/book/home/info")
    public Wrapper<HomeInfoVo> homeInfo(){
        return bookAggregationService.homeInfo();
    }

}
