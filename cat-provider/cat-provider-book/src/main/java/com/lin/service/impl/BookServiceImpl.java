package com.lin.service.impl;

import com.lin.dao.BookMapper;
import com.lin.dao.BookTypeMapper;
import com.lin.dao.CommentMapper;
import com.lin.dto.*;
import com.lin.model.Book;
import com.lin.model.BookType;
import com.lin.model.Comment;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import com.lin.vo.BookUrlVo;
import com.lin.vo.CommentListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lzr
 * @date 2019-11-25 16:01:39
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    private final BookTypeMapper bookTypeMapper;

    private final CommentMapper commentMapper;

    public BookServiceImpl(BookMapper bookMapper, BookTypeMapper bookTypeMapper, CommentMapper commentMapper) {
        this.bookMapper = bookMapper;
        this.bookTypeMapper = bookTypeMapper;
        this.commentMapper = commentMapper;
    }

    /**
     * 获取书籍列表
     * @param bookListDTO
     * @param page
     * @return 返回书籍列表
     */
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Page page) {
        int totalCount = bookMapper.searchBookListCount(bookListDTO);
        List<BookListVo> bookList = null;
        if(0 < totalCount){
            bookList = bookMapper.searchBookList(bookListDTO, page.getPage(), page.getRows());
        }
        return Wrapper.success(bookList, totalCount);
    }

    /**
     * 获取书籍详情
     * @param baseBookDTO 书籍id
     * @return 返回书籍详情
     */
    @Override
    public Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO) {
        Book book = bookMapper.findById(baseBookDTO.getBookId());
        if(null == book){
            return Wrapper.fail("没有该书籍");
        }
        return Wrapper.success(book);
    }

    /**
     * 新增书籍
     * @param bookAddDTO
     * @return 返回新增书籍信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper bookAdd(BookAddDTO bookAddDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookAddDTO, book);
        book.setId(new SnowFlake(0, 0).nextId());
        book.setBookTypeId(new SnowFlake(0, 0).nextId());
        book.setUploadTime(System.currentTimeMillis());
        book.setState(1);
        bookMapper.insert(book);
        BookType bookType = new BookType();
        bookType.setId(book.getBookTypeId());
        bookType.setBookTypeName(bookAddDTO.getBookTypeName());
        bookTypeMapper.insert(bookType);
        return Wrapper.success();
    }

    /**
     * 更新书籍
     * @param bookUpdateDTO
     * @return 返回更新书籍信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper bookUpdate(BookUpdateDTO bookUpdateDTO) {
        Book book = bookMapper.findById(bookUpdateDTO.getBookId());
        if(null == book){
            return Wrapper.fail("没有该书籍");
        }
        book.setAmount(bookUpdateDTO.getAmount());
        book.setBookQuantity(bookUpdateDTO.getBookQuantity());
        bookMapper.update(book);
        BookType bookType = bookTypeMapper.findById(book.getBookTypeId());
        bookType.setBookTypeName(bookUpdateDTO.getBookTypeName());
        bookTypeMapper.update(bookType);
        BeanUtils.copyProperties(book, bookUpdateDTO);
        return Wrapper.success();
    }

    /**
     * 删除书籍
     * @param bookDeleteDTO
     * @return 返回删除书籍信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> bookDelete(BookDeleteDTO bookDeleteDTO) {
        List<BookInfoVo> bookInfoVoList = bookMapper.searchBookInfoList(bookDeleteDTO.getBookIds());
        if(0 != bookInfoVoList.size()){
            bookInfoVoList.forEach(bookInfoVo -> {
                bookInfoVo.setState(0);
                bookInfoVo.setRemoveTime(String.valueOf(System.currentTimeMillis()));
                Book book = new Book();
                BeanUtils.copyProperties(bookInfoVo, book);
                bookMapper.update(book);
            });
        }
        return Wrapper.success();
    }

    /**
     * 上传书籍
     * @param file 路径
     * @return
     */
    @Override
    public Wrapper<BookUrlVo> bookUpload(MultipartFile file) {
        if(null == file) {
            log.info("上传文件为空");
            return Wrapper.fail("上传文件为空");
        }
        String path = "/Users/lzr/Pictures/cat";
        File filePath = new File(path);
        try {
            //上传到指定路径
            file.transferTo(filePath);
        }catch(Exception e){
            log.info("上传文件失败", e);
        }
        // 重新生成唯一文件名，用于存储数据库
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName;
        String bookUrl = path + newFileName;
        BookUrlVo bookUrlVo = new BookUrlVo();
        bookUrlVo.setBookUrl(bookUrl);
        return Wrapper.success(bookUrlVo);
    }

    /**
     * 获取书籍详情列表
     * @param ids 多个书籍id，以逗号隔开的字符串
     * @return 返回书籍详情列表
     */
    @Override
    public Wrapper<List<BookInfoVo>> bookInfoList(List<Long> ids) {
        log.info("bookIds: {}", ids);
        List<BookInfoVo> bookInfoVoList = bookMapper.searchBookInfoList(ids);
        return Wrapper.success(bookInfoVoList);
    }

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page
     * @return 返回书籍评论列表
     */
    @Override
    public Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Page page) {
        int count = commentMapper.searchCommentListCount(commentListDTO);
        List<CommentListVo> commentListVoList = new ArrayList<>();
        if(0 < count) {
            commentListVoList = commentMapper.searchCommentList(commentListDTO, page);
        }
        return Wrapper.success(commentListVoList, count);
    }

    /**
     * 添加评论
     * @param commentInsetDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO) {
        Comment comment = new Comment();
        comment.setBookId(commentInsetDTO.getBookId());
        comment.setId(new SnowFlake(0, 0).nextId());
        comment.setScore(commentInsetDTO.getScore());
        comment.setContent(commentInsetDTO.getContent());
        comment.setCreateTime(System.currentTimeMillis());
        commentMapper.insert(comment);
        return Wrapper.success();
    }

    /**
     * 更改书籍状态
     * @param bookStateAdjustDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO) {
        Book book = new Book();
        book.setId(bookStateAdjustDTO.getBookId());
        book.setState(bookStateAdjustDTO.getState());
        bookMapper.update(book);
        return Wrapper.success();
    }
}
