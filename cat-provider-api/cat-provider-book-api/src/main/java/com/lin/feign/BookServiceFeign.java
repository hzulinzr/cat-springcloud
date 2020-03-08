package com.lin.feign;

import com.lin.dto.*;
import com.lin.fallback.BookServiceFallback;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-06 19:21:47
 */
@FeignClient(name = "cat-provider-book", fallback = BookServiceFallback.class)
public interface BookServiceFeign {
    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    @GetMapping("/book/list")
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, @RequestParam Integer page, @RequestParam Integer rows);

    /**
     * 获取书籍详情列表
     * @param ids 书籍id列表
     * @return 返回书籍详情列表
     */
    @GetMapping("/book/info/list")
    Wrapper<List<BookInfoVo>> bookInfoList(@RequestParam List<Long> ids);

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    @GetMapping("/book/info")
    Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO);

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    @PostMapping("/book/state/adjust")
    Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO);

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍评论列表
     */
    @GetMapping("/comment/list")
    Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Integer page, Integer rows);

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @PostMapping("/comment/insert")
    Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO);
}
