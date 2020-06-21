package com.lin.service;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-07 12:32:19
 */
public interface BookAggregationService {
    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, int page, int rows);

    /**
     * 获取书籍详情
     * @param bookInfoDTO 书籍id
     * @return 返回书籍详情
     */
    Wrapper<BookInfoAllVo> bookInfo(BookInfoDTO bookInfoDTO);

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO);

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍评论列表
     */
    Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Integer page, Integer rows);

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO);
    /**
     * 删除评论
     * @param commentDeleteDTO
     * @return
     */
    Wrapper<Void> commentDelete(CommentDeleteDTO commentDeleteDTO);
    /**
     * 管理后台首页的接口
     * @return
     */
    Wrapper<HomeInfoVo> homeInfo();
    /**
     * 新增书籍
     * @param bookAddDTO
     * @return
     */
    Wrapper<Void> bookAdd(BookAddDTO bookAddDTO);

    /**
     * 上传书籍
     * @param file
     * @return 返回书籍路径
     */
    Wrapper<BookUrlVo> bookUpload(MultipartFile file);
    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return
     */
    Wrapper<Void> bookUpdate(BookUpdateDTO bookUpdateDTO);
    /**
     * 查看收藏列表
     * @param bookCollectDTO
     * @param rows 行数
     * @param page 页码
     * @return 返回收藏列表
     */
    Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Integer page, Integer rows);
    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO);
    /**
     * 更改书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO);
    /**
     * 每日推荐
     * @return 返回点赞数最高的前六本书籍
     */
    Wrapper<List<BookRecommendVo>> bookRecommend();
}
