package com.lin.service.impl;

import com.lin.dao.BookMapper;
import com.lin.dao.BookTypeMapper;
import com.lin.dto.BaseBookDTO;
import com.lin.dto.BookAddDTO;
import com.lin.dto.BookListDTO;
import com.lin.dto.BookUpdateDTO;
import com.lin.model.Book;
import com.lin.model.BookType;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.BookService;
import com.lin.tools.Page;
import com.lin.tools.SnowFlake;
import com.lin.vo.BookListVo;
import com.lin.vo.BookUrlVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    public BookServiceImpl(BookMapper bookMapper, BookTypeMapper bookTypeMapper) {
        this.bookMapper = bookMapper;
        this.bookTypeMapper = bookTypeMapper;
    }

    /**
     * 获取书籍列表
     * @param bookListDTO
     * @param page
     * @return 返回书籍列表
     */
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Page page) {
        int totalCount = bookMapper.searchBookListCount(bookListDTO.getKeyword());
        List<BookListVo> bookList = null;
        if(0 < totalCount){
            bookList = bookMapper.searchBookList(bookListDTO.getKeyword(), page.getPage(), page.getRows());
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
    public Wrapper bookAdd(BookAddDTO bookAddDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookAddDTO, book);
        book.setId(new SnowFlake(0, 0).nextId());
        book.setBookTypeId(new SnowFlake(0, 0).nextId());
        book.setUploadTime(String.valueOf(System.currentTimeMillis()));
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
     * @param baseBookDTO
     * @return 返回删除书籍信息
     */
    @Override
    public Wrapper bookDelete(BaseBookDTO baseBookDTO) {
        Book book = bookMapper.findById(baseBookDTO.getBookId());
        if(null == book){
            return Wrapper.fail("没有该书籍");
        }
        book.setState(0);
        book.setRemoveTime(String.valueOf(System.currentTimeMillis()));
        bookMapper.update(book);
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
            log.info("上传文件失败");
        }
        // 重新生成唯一文件名，用于存储数据库
        String fileName = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName;
        String bookUrl = path + newFileName;
        BookUrlVo bookUrlVo = new BookUrlVo();
        bookUrlVo.setBookUrl(bookUrl);
        return Wrapper.success(bookUrlVo);
    }
}
