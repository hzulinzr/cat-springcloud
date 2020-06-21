package com.lin.controller;

import com.lin.dto.ThumbsUpInfoDTO;
import com.lin.dto.ThumbsUpInsertDTO;
import com.lin.dto.ThumbsUpUpdateDTO;
import com.lin.model.ThumbsUp;
import com.lin.response.Wrapper;
import com.lin.service.ThumbsUpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-04-18 00:24:56
 */
@RestController
public class ThumbsUpController {
    private ThumbsUpService thumbsUpService;

    public ThumbsUpController(ThumbsUpService thumbsUpService) {
        this.thumbsUpService = thumbsUpService;
    }

    /**
     * 获取用户对书籍的点赞详情
     * @param thumbsUpInfoDTO
     * @return 返回用户对书籍的点赞详情
     */
    @GetMapping("/book/thumbsUp/info")
    public Wrapper<ThumbsUp> thumbsUpInfo(ThumbsUpInfoDTO thumbsUpInfoDTO){
        return thumbsUpService.thumbsUpInfo(thumbsUpInfoDTO);
    }
    /**
     * 更改用户的点赞状态
     * @param thumbsUpUpdateDTO
     * @return
     */
    @PostMapping("/book/thumbsUp/update")
    public Wrapper<Void> thumbsUpUpdate(@RequestBody ThumbsUpUpdateDTO thumbsUpUpdateDTO){
        return thumbsUpService.thumbsUpUpdate(thumbsUpUpdateDTO);
    }

    /**
     * 插入用户对书籍的点赞信息
     * @param thumbsUpUpdateDTO
     * @return
     */
    @PostMapping("/book/thumbsUp/insert")
    public Wrapper<Void> thumbsUpInsert(@RequestBody ThumbsUpUpdateDTO thumbsUpUpdateDTO){
        return thumbsUpService.thumbsUpInsert(thumbsUpUpdateDTO);
    }
}
