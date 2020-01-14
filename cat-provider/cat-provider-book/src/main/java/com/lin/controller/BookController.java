package com.lin.controller;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookService;
import com.lin.tools.Page;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import com.lin.vo.BookUrlVo;
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
     * @param baseBookDTO
     * @return 返回删除书籍信息
     */
    @PostMapping("/book/delete")
    public Wrapper bookDelete(@RequestBody BaseBookDTO baseBookDTO){
        return bookService.bookDelete(baseBookDTO);
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
    public Wrapper<List<BookInfoVo>> bookInfoList(String ids){
        return bookService.bookInfoList(ids);
    }
}
