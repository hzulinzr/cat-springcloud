package com.lin.service;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.tools.Page;
import com.lin.vo.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lzr
 * @date 2019-11-25 16:01:16
 */
public interface BookService {

    /**
     * 获取书籍列表
     * @param bookListDTO
     * @param page
     * @return 返回书籍列表
     */
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Page page);

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO);

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return 返回新增书籍信息
     */
    Wrapper<Void> bookAdd(BookAddDTO bookAddDTO);

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return 返回更新书籍信息
     */
    Wrapper<Void> bookUpdate(BookUpdateDTO bookUpdateDTO);

    /**
     * 删除书籍
     * @param bookDeleteDTO
     * @return 返回删除书籍信息
     */
    Wrapper<Void> bookDelete(BookDeleteDTO bookDeleteDTO);

    /**
     * 上传书籍
     * @param file 路径
     * @return
     */
    Wrapper<BookUrlVo> bookUpload(@RequestParam("file") MultipartFile file);

    /**
     * 获取书籍详情列表
     * @param ids 多个书籍id，以逗号隔开的字符串
     * @return 返回书籍详情列表
     */
    Wrapper<List<BookInfoVo>> bookInfoList(List<Long> ids);

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page
     * @return 返回书籍评论列表
     */
    Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Page page);
    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO);

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO);
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
     * 每日推荐
     * @return 返回点赞数最高的前六本书籍
     */
    Wrapper<List<BookRecommendVo>> bookRecommend();

}

