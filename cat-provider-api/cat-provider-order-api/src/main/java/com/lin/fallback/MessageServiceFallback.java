package com.lin.fallback;

import com.lin.dto.MessageInsertDTO;
import com.lin.dto.MessageListDTO;
import com.lin.feign.MessageServiceFeign;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.vo.MessageListInfoVo;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2020-03-20 22:32:35
 */
@Component
public class MessageServiceFallback implements MessageServiceFeign {
    @Override
    public Wrapper<PageData<MessageListInfoVo>> messageList(MessageListDTO messageListDTO, int page, int rows) {
        return null;
    }

    @Override
    public Integer messageUnreadCount(MessageListDTO messageListDTO) {
        return null;
    }

    @Override
    public Wrapper<PageData<MessageListInfoVo>> messageAdjust(MessageListDTO messageListDTO) {
        return null;
    }

    @Override
    public Wrapper<Void> messageInsert(MessageInsertDTO messageInsertDTO) {
        return null;
    }
}
