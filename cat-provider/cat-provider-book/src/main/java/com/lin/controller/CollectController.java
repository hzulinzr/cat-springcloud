package com.lin.controller;

import com.lin.dto.BookCollectDTO;
import com.lin.dto.BookCollectUpdateDTO;
import com.lin.model.Collect;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.CollectService;
import com.lin.tools.Page;
import com.lin.vo.BookCollectVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-04-18 00:24:40
 */
@RestController
public class CollectController {
    private CollectService collectService;

    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    /**
     * 获取用户收藏列表
     * @param bookCollectDTO
     * @param page
     * @return 返回用户收藏列表
     */
    @GetMapping("/book/collect/list")
    public Wrapper<PageData<BookCollectVo>> bookCollectList(BookCollectDTO bookCollectDTO, Page page){
        return collectService.bookCollectList(bookCollectDTO, page);
    }

    /**
     * 更改收藏状态
     * @param bookCollectUpdateDTO
     * @return
     */
    @PostMapping("/book/collect/update")
    public Wrapper<Void> bookCollectUpdate(@RequestBody BookCollectUpdateDTO bookCollectUpdateDTO){
        return collectService.bookCollectUpdate(bookCollectUpdateDTO);
    }

    /**
     * 获取收藏详情
     * @param bookCollectUpdateDTO
     * @return 返回收藏详情
     */
    @GetMapping("/book/collect/info")
    public Wrapper<Collect> collectInfo(BookCollectUpdateDTO bookCollectUpdateDTO){
        return collectService.collectInfo(bookCollectUpdateDTO);
    }

    /**
     * 插入收藏信息
     * @param bookCollectUpdateDTO
     * @return
     */
    @PostMapping("/book/collect/insert")
    public Wrapper<Void> bookCollectInsert(@RequestBody BookCollectUpdateDTO bookCollectUpdateDTO){
        return collectService.bookCollectInsert(bookCollectUpdateDTO);
    }
}
