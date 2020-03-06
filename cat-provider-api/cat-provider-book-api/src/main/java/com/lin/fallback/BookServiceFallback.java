package com.lin.fallback;

import com.lin.dto.*;
import com.lin.feign.BookServiceFeign;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import com.lin.vo.CommentListVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-06 19:23:26
 */
@Component
public class BookServiceFallback implements BookServiceFeign {
    @GetMapping("/book/list")
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, Integer page, Integer rows) {
        return Wrapper.fail(ResponseCode.BOOK_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<List<BookInfoVo>> bookInfoList(List<Long> ids) {
        return Wrapper.fail(ResponseCode.BOOK_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Book> bookInfo(BaseBookDTO baseBookDTO) {
        return Wrapper.fail(ResponseCode.BOOK_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<Void> bookStateAdjust(BookStateAdjustDTO bookStateAdjustDTO) {
        return Wrapper.fail(ResponseCode.BOOK_SERVICE_NO_AVAILABLE);
    }

    @Override
    public Wrapper<PageData<CommentListVo>> commentList(CommentListDTO commentListDTO, Integer page, Integer rows) {
        return null;
    }

    @Override
    public Wrapper<Void> commentInsert(CommentInsetDTO commentInsetDTO) {
        return null;
    }
}
