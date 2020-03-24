package com.lin.service;

import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
import com.lin.vo.HomeInfoVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO);

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
}
