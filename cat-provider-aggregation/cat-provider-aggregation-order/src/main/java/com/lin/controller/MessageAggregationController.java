package com.lin.controller;

import com.lin.dto.MessageAdjustDTO;
import com.lin.dto.MessageListDTO;
import com.lin.dto.MessageUnreadDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.MessageAggregationService;
import com.lin.vo.MessageListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-03-20 22:27:28
 */
@RestController
public class MessageAggregationController {
    private MessageAggregationService messageAggregationService;

    public MessageAggregationController(MessageAggregationService messageAggregationService) {
        this.messageAggregationService = messageAggregationService;
    }

    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    @GetMapping("/message/list")
    public Wrapper<PageData<MessageListVo>> messageList(MessageListDTO messageListDTO, int page, int rows){
        return messageAggregationService.messageList(messageListDTO, page, rows);
    }

    /**
     * 查看消息未读数量
     * @param messageUnreadDTO
     * @return 返回消息未读数量
     */
    @GetMapping("/message/unread/count")
    public Wrapper<Integer> messageUnreadCount(MessageUnreadDTO messageUnreadDTO){
        return messageAggregationService.messageUnreadCount(messageUnreadDTO);
    }

    /**
     * 消息调整
     * @param messageAdjustDTO
     * @return 返回消息列表
     */
    @PostMapping("/message/adjust")
    public Wrapper<PageData<MessageListVo>> messageAdjust(@RequestBody MessageAdjustDTO messageAdjustDTO){
        return messageAggregationService.messageAdjust(messageAdjustDTO);
    }

}
