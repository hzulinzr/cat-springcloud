package com.lin.service.impl;

import com.lin.dto.*;
import com.lin.feign.*;
import com.lin.model.AuthUser;
import com.lin.model.Book;
import com.lin.model.Collect;
import com.lin.model.ThumbsUp;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookAggregationService;
import com.lin.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private FileServiceFeign fileServiceFeign;

    public BookAggregationServiceImpl(BookServiceFeign bookServiceFeign, AuthUserServiceFeign authUserServiceFeign, OrderServiceFeign orderServiceFeign, MessageServiceFeign messageServiceFeign, FileServiceFeign fileServiceFeign) {
        this.bookServiceFeign = bookServiceFeign;
        this.authUserServiceFeign = authUserServiceFeign;
        this.orderServiceFeign = orderServiceFeign;
        this.messageServiceFeign = messageServiceFeign;
        this.fileServiceFeign = fileServiceFeign;
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
     * @param bookInfoDTO 书籍id
     * @return 返回书籍详情
     */
    @Override
    public Wrapper<BookInfoAllVo> bookInfo(BookInfoDTO bookInfoDTO) {
        BookInfoAllVo bookInfoVo = new BookInfoAllVo();
        //获取书籍详情
        BaseBookDTO baseBookDTO = new BaseBookDTO();
        baseBookDTO.setBookId(bookInfoDTO.getBookId());
        Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();
        if(book != null){
            BeanUtils.copyProperties(book, bookInfoVo);
            //获取用户的点赞状态
            ThumbsUpInfoDTO thumbsUpInfoDTO = new ThumbsUpInfoDTO();
            thumbsUpInfoDTO.setBookId(bookInfoDTO.getBookId());
            thumbsUpInfoDTO.setUserId(bookInfoDTO.getUserId());
            ThumbsUp thumbsUp = bookServiceFeign.thumbsUpInfo(thumbsUpInfoDTO).getData();
            if(null != thumbsUp){
                bookInfoVo.setThumbsUpState(thumbsUp.getState());
            }else {
                //状态：未点赞
                bookInfoVo.setThumbsUpState(0);
            }


            //获取用户的收藏状态
            BookCollectUpdateDTO bookCollectUpdateDTO = new BookCollectUpdateDTO();
            bookCollectUpdateDTO.setUserId(bookInfoDTO.getUserId());
            bookCollectUpdateDTO.setBookId(bookInfoDTO.getBookId());
            Collect collect = bookServiceFeign.collectInfo(bookCollectUpdateDTO).getData();
            if(collect != null){
                bookInfoVo.setCollectState(collect.getState());
            }else{
                //状态：未收藏
                bookInfoVo.setCollectState(0);
            }

        }
        return Wrapper.success(bookInfoVo);
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
                AuthUser authUser = authUserServiceFeign.userInfo(baseAuthUser).getData();
                commentListVo.setUsername(authUser.getClientId());
                commentListVo.setUserUrl(authUser.getUserUrl());
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

        //查看被评论的用户详情
        BaseAuthUser baseAuthUserNew = new BaseAuthUser();
        baseAuthUserNew.setUserId(book.getUploadUserId());
        AuthUser authUserNew = authUserServiceFeign.userInfo(baseAuthUserNew).getData();

        //添加评论信息
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        String content = "你的书籍：" + book.getBookName() + "被" + authUser.getClientId() + "评论";
        messageInsertDTO.setContent(content);
        messageInsertDTO.setBookId(commentInsetDTO.getBookId());
        //类型为1：评论
        messageInsertDTO.setType(1);
        messageInsertDTO.setUserId(authUserNew.getId());
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

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return
     */
    @Override
    public Wrapper<Void> bookAdd(BookAddDTO bookAddDTO) {
        return bookServiceFeign.bookAdd(bookAddDTO);
    }

    /**
     * 上传书籍
     * @param file
     * @return 返回书籍路径
     */
    @Override
    public Wrapper<BookUrlVo> bookUpload(MultipartFile file) {
        return fileServiceFeign.bookUpload(file);
    }

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> bookUpdate(BookUpdateDTO bookUpdateDTO) {
        return bookServiceFeign.bookUpdate(bookUpdateDTO);
    }

    /**
     * 查看收藏列表
     * @param bookCollectDTO
     * @param rows 行数
     * @param page 页码
     * @return 返回收藏列表
     */
    @Override
    public Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Integer page, Integer rows) {
        List<BookCollectVo> bookCollectVos = new ArrayList<>();
        //获取用户收藏的列表
        PageData<BookCollectVo> bookCollectVoWrapper = bookServiceFeign.bookCollectList(bookCollectDTO, page, rows).getData();
        int count = bookCollectVoWrapper.getTotalCount();
        if(0 < count) {
            bookCollectVos = bookCollectVoWrapper.getData();
            bookCollectVos.forEach(bookCollectVo -> {
                //获取书籍对应名称
                BaseBookDTO baseBookDTO = new BaseBookDTO();
                baseBookDTO.setBookId(bookCollectVo.getBookId());
                Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();
                bookCollectVo.setBookName(book.getBookName());
                bookCollectVo.setBookUrl(book.getBookUrl());
            });
        }
        return Wrapper.success(bookCollectVos, count);
    }

    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO) {
        //获取用户收藏id
        Collect collect = bookServiceFeign.collectInfo(bookCollectUpdateDTO).getData();
        if(collect != null){
            bookCollectUpdateDTO.setId(collect.getId());
            //更改用户收藏状态
            bookServiceFeign.bookCollectUpdate(bookCollectUpdateDTO);
        }else{
            //插入收藏
            bookServiceFeign.bookCollectInsert(bookCollectUpdateDTO);
        }
        return Wrapper.success();
    }

    /**
     * 更改书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    @Override
    public Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO) {
        //更改书籍的点赞数
        BookUpdateDTO bookUpdateDTO = new BookUpdateDTO();
        bookUpdateDTO.setBookId(thumbsUpUpdateDTO.getBookId());
        bookUpdateDTO.setThumbsUp(thumbsUpUpdateDTO.getThumbsUp());
        bookServiceFeign.bookUpdate(bookUpdateDTO);

        //获取用户对书籍点赞的详情
        ThumbsUpInfoDTO thumbsUpInfoDTO = new ThumbsUpInfoDTO();
        thumbsUpInfoDTO.setBookId(thumbsUpUpdateDTO.getBookId());
        thumbsUpInfoDTO.setUserId(thumbsUpUpdateDTO.getUserId());
        ThumbsUp thumbsUp = bookServiceFeign.thumbsUpInfo(thumbsUpInfoDTO).getData();
        if(null != thumbsUp){
            //更改用户对该书籍的点赞状态
            thumbsUpUpdateDTO.setId(thumbsUp.getId());
            bookServiceFeign.thumbsUpUpdate(thumbsUpUpdateDTO);

        }else{
            //插入用户对书籍的点赞信息
            bookServiceFeign.thumbsUpInsert(thumbsUpUpdateDTO);
        }

        return Wrapper.success();
    }

    /**
     * 每日推荐
     * @return 返回点赞数最高的前六本书籍
     */
    @Override
    public Wrapper<List<BookRecommendVo>> bookRecommend() {
        return bookServiceFeign.bookRecommend();
    }

}
