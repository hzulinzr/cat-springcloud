package com.lin.feign;

import com.lin.dto.*;
import com.lin.fallback.BookServiceFallback;
import com.lin.model.Book;
import com.lin.model.Collect;
import com.lin.model.ThumbsUp;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.*;
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
     * @param rows 行数int
     * @return 返回书籍列表
     */
    @GetMapping("/book/list")
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, @RequestParam int page,  @RequestParam int rows);

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
    Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, @RequestParam Integer page, @RequestParam Integer rows);

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @PostMapping("/comment/insert")
    Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO);

    /**
     * 删除评论
     * @param commentDeleteDTO
     * @return
     */
    @PostMapping("/comment/delete")
    Wrapper<Void> commentDelete(CommentDeleteDTO commentDeleteDTO);

    /**
     * 管理后台首页的接口
     * @return
     */
    @GetMapping("/book/home/info")
    Wrapper<HomeInfoVo> homeInfo();

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return 返回新增书籍信息
     */
    @PostMapping("/book/add")
    Wrapper<Void> bookAdd(BookAddDTO bookAddDTO);

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return 返回更新书籍信息
     */
    @PostMapping("/book/update")
    Wrapper<Void> bookUpdate(BookUpdateDTO bookUpdateDTO);

    /**
     * 获取用户收藏列表
     * @param bookCollectDTO
     * @param page
     * @param rows
     * @return 返回用户收藏列表
     */
    @GetMapping("/book/collect/list")
    Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO,  @RequestParam int page,  @RequestParam int rows);
    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    @PostMapping("/book/collect/update")
    Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO);

    /**
     * 获取收藏详情
     * @param bookCollectUpdateDTO
     * @return 返回收藏详情
     */
    @GetMapping("/book/collect/info")
    Wrapper<Collect> collectInfo(BookCollectUpdateDTO bookCollectUpdateDTO);

    /**
     * 插入收藏信息
     * @param bookCollectUpdateDTO
     * @return
     */
    @PostMapping("/book/collect/insert")
    Wrapper<Void> bookCollectInsert( BookCollectUpdateDTO bookCollectUpdateDTO);

    /**
     * 获取用户对书籍的点赞详情
     * @param thumbsUpInfoDTO
     * @return 返回用户对书籍的点赞详情
     */
    @GetMapping("/book/thumbsUp/info")
    Wrapper<ThumbsUp> thumbsUpInfo(ThumbsUpInfoDTO thumbsUpInfoDTO);
    /**
     * 更改用户的点赞状态
     * @param thumbsUpUpdateDTO
     * @return
     */
    @PostMapping("/book/thumbsUp/update")
    Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO);

    /**
     * 插入用户对书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    @PostMapping("/book/thumbsUp/insert")
    Wrapper<Void> thumbsUpInsert(ThumbsUpUpdateDTO thumbsUpUpdateDTO);

    /**
     * 每日推荐
     * @return 返回点赞数最高的前六本书籍
     */
    @GetMapping("/book/recommend")
    Wrapper<List<BookRecommendVo>> bookRecommend();


}
