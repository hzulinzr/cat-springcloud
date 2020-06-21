package com.lin.controller;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * @param bookInfoDTO 书籍id
     * @return 返回书籍详情
     */
    @GetMapping("/book/info")
    public Wrapper<BookInfoAllVo> bookInfo(BookInfoDTO bookInfoDTO){
        return bookAggregationService.bookInfo(bookInfoDTO);
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

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return
     */
    @PostMapping("/book/add")
    public Wrapper<Void> bookAdd(@RequestBody BookAddDTO bookAddDTO){
        return bookAggregationService.bookAdd(bookAddDTO);
    }

    /**
     * 上传书籍
     * @param file
     * @return 返回书籍路径
     */
    @PostMapping("/book/upload")
    public Wrapper<BookUrlVo> bookUpload(@RequestPart("file") MultipartFile file){
        return bookAggregationService.bookUpload(file);
    }

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return
     */
    @PostMapping("/book/update")
    public Wrapper<Void> bookUpdate(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookAggregationService.bookUpdate(bookUpdateDTO);
    }

    /**
     * 查看收藏列表
     * @param bookCollectDTO
     * @param rows 行数
     * @param page 页码
     * @return 返回收藏列表
     */
    @GetMapping("/book/collect/list")
    public Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Integer page, Integer rows){
        return bookAggregationService.bookCollectList(bookCollectDTO, page, rows);
    }

    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    @PostMapping("/book/collect/update")
    public Wrapper<Void> bookCollectUpdate(@RequestBody BookCollectUpdateDTO bookCollectUpdateDTO){
        return bookAggregationService.bookCollectUpdate(bookCollectUpdateDTO);
    }

    /**
     * 更改书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    @PostMapping("/book/thumbsUp/update")
    public Wrapper<Void> thumbsUpUpdate(@RequestBody ThumbsUpUpdateDTO thumbsUpUpdateDTO){
        return bookAggregationService.thumbsUpUpdate(thumbsUpUpdateDTO);
    }

    /**
     * 每日推荐
     * @return 返回点赞数最高的前六本书籍
     */
    @GetMapping("/book/recommend")
    public Wrapper<List<BookRecommendVo>> bookRecommend(){
        return bookAggregationService.bookRecommend();
    }

}
