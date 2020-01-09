package com.lin.fallback;

import com.lin.dto.BookListDTO;
import com.lin.feign.BookServiceFeign;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.BookListVo;
import org.springframework.stereotype.Component;

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
}
