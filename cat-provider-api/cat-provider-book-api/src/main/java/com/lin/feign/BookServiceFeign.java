package com.lin.feign;

import com.lin.dto.BookListDTO;
import com.lin.fallback.BookServiceFallback;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.BookInfoVo;
import com.lin.vo.BookListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-06 19:21:47
 */
@FeignClient(name = "cat-provider-book", fallback = BookServiceFallback.class)
public interface BookServiceFeign {
    @GetMapping("/book/list")
    Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, @RequestParam Integer page, @RequestParam Integer rows);

    @GetMapping("/book/info/list")
    Wrapper<List<BookInfoVo>> bookInfoList(String ids);
}
