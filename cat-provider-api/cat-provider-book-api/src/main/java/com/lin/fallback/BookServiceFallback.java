package com.lin.fallback;

import com.lin.dto.BaseBookDTO;
import com.lin.dto.BookListDTO;
import com.lin.feign.BookServiceFeign;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-06 19:23:26
 */
@Component
public class BookServiceFallback implements BookServiceFeign {
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
}
