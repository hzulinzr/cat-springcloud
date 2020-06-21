package com.lin.fallback;

import com.lin.dto.*;
import com.lin.feign.BookServiceFeign;
import com.lin.model.Book;
import com.lin.model.Collect;
import com.lin.model.ThumbsUp;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lzr
 * @date 2020-01-06 19:23:26
 */
@Component
public class BookServiceFallback implements BookServiceFeign {
    @Override
    public Wrapper<PageData<BookListVo>> bookList(BookListDTO bookListDTO, int page, int rows) {
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

    @Override
    public Wrapper<Void> commentDelete(CommentDeleteDTO commentDeleteDTO) {
        return null;
    }

    @Override
    public Wrapper<HomeInfoVo> homeInfo() {
        return null;
    }

    @Override
    public Wrapper<Void> bookAdd(BookAddDTO bookAddDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> bookUpdate(BookUpdateDTO bookUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, int page, int rows) {
        return null;
    }

    @Override
    public Wrapper<Void> bookCollectUpdate(BookCollectUpdateDTO bookCollectUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<Collect> collectInfo(BookCollectUpdateDTO bookCollectUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> bookCollectInsert(BookCollectUpdateDTO bookCollectUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<ThumbsUp> thumbsUpInfo(ThumbsUpInfoDTO thumbsUpInfoDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> thumbsUpUpdate(ThumbsUpUpdateDTO thumbsUpUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> thumbsUpInsert(ThumbsUpUpdateDTO thumbsUpUpdateDTO) {
        return null;
    }

    @Override
    public Wrapper<List<BookRecommendVo>> bookRecommend() {
        return null;
    }

}
