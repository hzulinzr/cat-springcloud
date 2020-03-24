package com.lin.service.impl;

import com.lin.dto.*;
import com.lin.feign.AuthUserServiceFeign;
import com.lin.feign.BookServiceFeign;
import com.lin.feign.MessageServiceFeign;
import com.lin.feign.OrderServiceFeign;
import com.lin.model.AuthUser;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
import com.lin.vo.HomeInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-01-07 12:35:38
 */
@Slf4j
@Service
public class BookAggregationServiceImpl implements BookAggregationService {

    private BookServiceFeign bookServiceFeign;
    private AuthUserServiceFeign authUserServiceFeign;
    private OrderServiceFeign orderServiceFeign;
    private MessageServiceFeign messageServiceFeign;

    public BookAggregationServiceImpl(BookServiceFeign bookServiceFeign, AuthUserServiceFeign authUserServiceFeign, OrderServiceFeign orderServiceFeign, MessageServiceFeign messageServiceFeign) {
        this.bookServiceFeign = bookServiceFeign;
        this.authUserServiceFeign = authUserServiceFeign;
        this.orderServiceFeign = orderServiceFeign;
        this.messageServiceFeign = messageServiceFeign;
    }


    /**
     * 获取书籍列表
     * @param bookListDTO 书籍实体类
     * @param page 页码
     * @param rows 行数
     * @return 返回书籍列表
     */
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, int page, int rows) {
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
        //更改书籍状态
        bookServiceFeign.bookStateAdjust(bookStateAdjustDTO);

        //查看书籍详情
        BaseBookDTO baseBookDTO = new BaseBookDTO();
        baseBookDTO.setBookId(bookStateAdjustDTO.getBookId());
        Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();

        //查看用户详情
        BaseAuthUser baseAuthUser = new BaseAuthUser();
        baseAuthUser.setUserId(book.getUploadUserId());
        AuthUser authUser = authUserServiceFeign.userInfo(baseAuthUser).getData();

        //添加评论信息
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        String state = "";
        if(bookStateAdjustDTO.getState() == 1){
            state = "上架";
        }else if(bookStateAdjustDTO.getState() == 0){
            state = "下架";
        }else if(bookStateAdjustDTO.getState() == -1){
            state = "审核未通过";
        }

        String content = "你的书籍：" + book.getBookName() + state + '了';
        messageInsertDTO.setContent(content);
        messageInsertDTO.setBookId(bookStateAdjustDTO.getBookId());
        //类型为2：审核
        messageInsertDTO.setType(2);
        messageInsertDTO.setUserId(authUser.getId());
        messageServiceFeign.messageInsert(messageInsertDTO);
        return Wrapper.success();
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
        PageData<CommentListVo> commentListVoPageData = bookServiceFeign.commentList(commentListDTO, page, rows).getData();
        int count = 0;
        count = commentListVoPageData.getTotalCount();
        List<CommentListVo> commentListVoList = new ArrayList<>();
        if(0 < count){
            commentListVoList = commentListVoPageData.getData();
            commentListVoList.forEach(commentListVo -> {
                //根据用户id查找用户名
                BaseAuthUser baseAuthUser = new BaseAuthUser();
                baseAuthUser.setUserId(commentListVo.getUserId());
                commentListVo.setUsername(authUserServiceFeign.userInfo(baseAuthUser).getData().getClientId());
                //根据bookId查找书籍名称
                BaseBookDTO baseBookDTO = new BaseBookDTO();
                baseBookDTO.setBookId(commentListVo.getBookId());
                commentListVo.setBookName(bookServiceFeign.bookInfo(baseBookDTO).getData().getBookName());
            });
        }
        return Wrapper.success(commentListVoList, count);
    }

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @Override
    public Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO) {

        bookServiceFeign.commentInsert(commentInsetDTO);
        //更新书籍订单关联表的状态为已完成
        BookOrderUpdateDTO bookOrderUpdateDTO = new BookOrderUpdateDTO();
        bookOrderUpdateDTO.setBookId(commentInsetDTO.getBookId());
        bookOrderUpdateDTO.setUserId(commentInsetDTO.getUserId());
        orderServiceFeign.orderBookUpdate(bookOrderUpdateDTO);

        //查看书籍详情
        BaseBookDTO baseBookDTO = new BaseBookDTO();
        baseBookDTO.setBookId(commentInsetDTO.getBookId());
        Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();

        //查看用户详情
        BaseAuthUser baseAuthUser = new BaseAuthUser();
        baseAuthUser.setUserId(commentInsetDTO.getUserId());
        AuthUser authUser = authUserServiceFeign.userInfo(baseAuthUser).getData();

        //添加评论信息
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        String content = "你的书籍：" + book.getBookName() + "被" + authUser.getClientId() + "评论";
        messageInsertDTO.setContent(content);
        messageInsertDTO.setBookId(commentInsetDTO.getBookId());
        //类型为1：评论
        messageInsertDTO.setType(1);
        messageInsertDTO.setUserId(authUser.getId());
        messageInsertDTO.setUploadUserId(commentInsetDTO.getUserId());
        messageServiceFeign.messageInsert(messageInsertDTO);

        return Wrapper.success();
    }

    /**
     * 删除评论
     * @param commentDeleteDTO
     * @return
     */
    @Override
    public Wrapper<Void> commentDelete(CommentDeleteDTO commentDeleteDTO) {
        return bookServiceFeign.commentDelete(commentDeleteDTO);
    }

    /**
     * 管理后台首页的接口
     * @return
     */
    @Override
    public Wrapper<HomeInfoVo> homeInfo() {
        return bookServiceFeign.homeInfo();
    }
}
