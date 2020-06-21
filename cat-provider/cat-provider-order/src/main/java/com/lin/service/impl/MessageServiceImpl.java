package com.lin.service.impl;

import com.lin.dao.MessageMapper;
import com.lin.dto.MessageInsertDTO;
import com.lin.dto.MessageListDTO;
import com.lin.model.Message;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.MessageService;
import com.lin.tools.SnowFlake;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;
import com.oracle.tools.packager.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-03-20 20:21:46
 */
@Service
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapper;
    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    @Override
    public Wrapper<PageData<MessageListInfoVo>> messageList(MessageListDTO messageListDTO, int page, int rows) {
        int count = 0;
        count = messageMapper.searchMessageListCount(messageListDTO);
        List<MessageListInfoVo> messageListVoList = new ArrayList<>();
        if(0 < count){
            messageListVoList = messageMapper.searchMessageList(messageListDTO, page, rows);
        }
        return Wrapper.success(messageListVoList, count);
    }

    /**
     * 查看消息不同类型未读的数量
     * @param messageListDTO
     * @return 返回消息不通类型未读的数量
     */
    @Override
    public Integer messageUnreadCount(MessageListDTO messageListDTO) {
        return messageMapper.searchMessageUnreadCount(messageListDTO);
    }

    /**
     * 调整消息列表的消息状态
     * @param messageListDTO
     * @return 返回消息列表
     */
    @Override
    public Wrapper<PageData<MessageListInfoVo>> messageAdjust(MessageListDTO messageListDTO) {
        List<Long> messageIds = messageListDTO.getMessageIds();
        if(null != messageIds && 0 != messageIds.size()){
            messageIds.forEach(messageId -> {
                Message message = new Message();
                message.setId(messageId);
                message.setState(1);
                messageMapper.update(message);
            });
            Log.info("更新消息为已读！");
        }
        return Wrapper.success();
    }

    /**
     * 添加消息
     * @param messageInsertDTO
     * @return
     */
    @Override
    public Wrapper<Void> messageInsert(MessageInsertDTO messageInsertDTO) {
        Message message = new Message();
        message.setId(new SnowFlake(0, 0).nextId());
        message.setCreateTime(System.currentTimeMillis());
        //未读
        message.setState(0);
        BeanUtils.copyProperties(messageInsertDTO, message);
        messageMapper.insert(message);
        Log.info("插入消息数据成功！");
        return Wrapper.success();
    }
}
