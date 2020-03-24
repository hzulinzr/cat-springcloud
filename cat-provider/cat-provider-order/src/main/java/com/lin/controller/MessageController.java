package com.lin.controller;

import com.lin.dto.MessageInsertDTO;
import com.lin.dto.MessageListDTO;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.MessageService;
import com.lin.tools.Page;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-03-20 20:20:21
 */
@RestController
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page
     * @return 返回消息列表
     */
    @GetMapping("/message/list")
    public Wrapper<PageData<MessageListInfoVo>> messageList(MessageListDTO messageListDTO, Page page){
        return messageService.messageList(messageListDTO, page.getPage(), page.getRows());
    }

    /**
     * 查看消息不同类型未读的数量
     * @param messageListDTO
     * @return 返回消息不通类型未读的数量
     */
    @GetMapping("/message/unread/count")
    public Integer messageUnreadCount(MessageListDTO messageListDTO){
        return messageService.messageUnreadCount(messageListDTO);
    }

    /**
     * 调整消息列表的消息状态
     * @param messageListDTO
     * @return 返回消息列表
     */
    @PostMapping("/message/adjust")
    public Wrapper<PageData<MessageListInfoVo>> messageAdjust(@RequestBody MessageListDTO messageListDTO){
        return messageService.messageAdjust(messageListDTO);
    }

    /**
     * 添加消息
     * @param messageInsertDTO
     * @return
     */
    @PostMapping("/message/insert")
    public Wrapper<Void> messageInsert(@RequestBody MessageInsertDTO messageInsertDTO){
        return messageService.messageInsert(messageInsertDTO);
    }

}
