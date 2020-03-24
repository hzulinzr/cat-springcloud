package com.lin.controller;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookService;
import com.lin.tools.Page;
import com.lin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lzr
 * @date 2019-11-25 15:55:44
 */
@RestController
@Api(tags = "书籍相关接口控制层")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 获取书籍列表
     * @param bookListDTO
     * @param page
     * @return 返回书籍列表
     */
    @ApiOperation(value = "获取书籍列表")
    @GetMapping("/book/list")
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Page page){
        return bookService.bookList(bookListDTO, page);
    }

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    @GetMapping("/book/info")
    public Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO){
        return bookService.bookInfo(baseBookDTO);
    }

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return 返回新增书籍信息
     */
    @PostMapping("/book/add")
    public Wrapper bookAdd(@RequestBody BookAddDTO bookAddDTO){
        return bookService.bookAdd(bookAddDTO);
    }

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return 返回更新书籍信息
     */
    @PostMapping("/book/update")
    public Wrapper bookUpdate(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookService.bookUpdate(bookUpdateDTO);
    }

    /**
     * 删除书籍
     * @param bookDeleteDTO
     * @return 返回删除书籍信息
     */
    @PostMapping("/book/delete")
    public Wrapper<Void> bookDelete(@RequestBody BookDeleteDTO bookDeleteDTO){
        return bookService.bookDelete(bookDeleteDTO);
    }

    /**
     * 上传书籍
     * @param file 路径
     * @return
     */
    @PostMapping("/book/upload")
    public Wrapper<BookUrlVo> bookUpload(@RequestParam("file") MultipartFile file){
        return bookService.bookUpload(file);
    }

    /**
     * 获取书籍详情列表
     * @param ids 多个书籍id，以逗号隔开的字符串
     * @return 返回书籍详情列表
     */
    @GetMapping("/book/info/list")
    public Wrapper<List<BookInfoVo>> bookInfoList(@RequestParam(value = "ids") List<Long> ids){
        return bookService.bookInfoList(ids);
    }

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page
     * @return 返回书籍评论列表
     */
    @GetMapping("/comment/list")
    public Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Page page){
        return bookService.commentList(commentListDTO, page);
    }

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @PostMapping("/comment/insert")
    public Wrapper<Void> commentInsert(@RequestBody CommentInsetDTO commentInsetDTO){
        return bookService.commentInsert(commentInsetDTO);
    }

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    @PostMapping("/book/state/adjust")
    public Wrapper<Void> bookStateAdjust(@RequestBody BookStateAdjustDTO bookStateAdjustDTO){
        return bookService.bookStateAdjust(bookStateAdjustDTO);
    }
    /**
     * 删除评论
     * @param commentDeleteDTO
     * @return
     */
    @PostMapping("/comment/delete")
    public Wrapper<Void> commentDelete(@RequestBody CommentDeleteDTO commentDeleteDTO){
        return bookService.commentDelete(commentDeleteDTO);
    }

    /**
     * 管理后台首页的接口
     * @return
     */
    @GetMapping("/book/home/info")
    public Wrapper<HomeInfoVo> homeInfo(){
        return bookService.homeInfo();
    }

}
