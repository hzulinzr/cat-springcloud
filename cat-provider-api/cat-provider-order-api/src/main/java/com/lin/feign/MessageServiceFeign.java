package com.lin.feign;

import com.lin.dto.MessageInsertDTO;
import com.lin.dto.MessageListDTO;
import com.lin.fallback.MessageServiceFallback;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lzr
 * @date 2020-03-20 22:31:59
 */
@FeignClient(name = "cat-provider-order", fallback = MessageServiceFallback.class)
public interface MessageServiceFeign {
    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    @GetMapping("/message/list")
    Wrapper<PageData<MessageListInfoVo>> messageList(MessageListDTO messageListDTO, @RequestParam int page, @RequestParam int rows);

    /**
     * 查看消息不同类型未读的数量
     * @param messageListDTO
     * @return 返回消息不通类型未读的数量
     */
    @GetMapping("/message/unread/count")
    Integer messageUnreadCount(MessageListDTO messageListDTO);

    /**
     * 调整消息列表的消息状态
     * @param messageListDTO
     * @return 返回消息列表
     */
    @PostMapping("/message/adjust")
    Wrapper<PageData<MessageListInfoVo>> messageAdjust(MessageListDTO messageListDTO);

    /**
     * 添加消息
     * @param messageInsertDTO
     * @return
     */
    @PostMapping("/message/insert")
    Wrapper<Void> messageInsert(MessageInsertDTO messageInsertDTO);
}
