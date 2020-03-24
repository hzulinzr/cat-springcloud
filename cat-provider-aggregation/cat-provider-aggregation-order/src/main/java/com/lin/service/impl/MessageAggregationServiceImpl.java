package com.lin.service.impl;

import com.lin.dto.MessageAdjustDTO;
import com.lin.dto.MessageListDTO;
import com.lin.dto.MessageUnreadDTO;
import com.lin.feign.MessageServiceFeign;
import com.lin.response.PageData;
import com.lin.response.Wrapper;
import com.lin.service.MessageAggregationService;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzr
 * @date 2020-03-20 22:28:00
 */
@Service
public class MessageAggregationServiceImpl implements MessageAggregationService {
    private MessageServiceFeign messageServiceFeign;

    public MessageAggregationServiceImpl(MessageServiceFeign messageServiceFeign) {
        this.messageServiceFeign = messageServiceFeign;
    }

    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    @Override
    public Wrapper<PageData<MessageListVo>> messageList(MessageListDTO messageListDTO, int page, int rows) {
        List<MessageListVo> messageListVo = new ArrayList<>();
        //获取消息列表
        PageData<MessageListInfoVo> messageListVoPageData = messageServiceFeign.messageList(messageListDTO, page, rows).getData();
        MessageListVo messageListVoList = new MessageListVo();
        messageListVoList.setMessageList(messageListVoPageData.getData());

        //获取不同类型消息未读的数量
        //未读
        messageListDTO.setState(0);
        //(消息类型, 1：评论)
        messageListDTO.setType(1);
        messageListVoList.setCommentUnreadCount(messageServiceFeign.messageUnreadCount(messageListDTO));
        //(消息类型, 2：审核)
        messageListDTO.setType(2);
        messageListVoList.setReviewUnreadCount(messageServiceFeign.messageUnreadCount(messageListDTO));
        //(消息类型, 3：通知)
        messageListDTO.setType(3);
        messageListVoList.setSystemUnreadCount(messageServiceFeign.messageUnreadCount(messageListDTO));

        messageListVo.add(messageListVoList);
        return Wrapper.success(messageListVo, messageListVoPageData.getTotalCount());
    }

    /**
     * 查看消息未读数量
     * @param messageUnreadDTO
     * @return 返回消息未读数量
     */
    @Override
    public Wrapper<Integer> messageUnreadCount(MessageUnreadDTO messageUnreadDTO) {
        MessageListDTO messageListDTO = new MessageListDTO();
        messageListDTO.setUserId(messageUnreadDTO.getUserId());
        //消息未读
        messageListDTO.setState(0);
        return Wrapper.success(messageServiceFeign.messageUnreadCount(messageListDTO));
    }

    /**
     * 消息调整
     * @param messageAdjustDTO
     * @return 返回消息列表
     */
    @Override
    public Wrapper<PageData<MessageListVo>> messageAdjust(MessageAdjustDTO messageAdjustDTO) {
        MessageListDTO messageListDTO = new MessageListDTO();
        BeanUtils.copyProperties(messageAdjustDTO, messageListDTO);
        //获取消息列表
        PageData<MessageListInfoVo> messageListVoPageData = messageServiceFeign.messageList(messageListDTO, messageAdjustDTO.getPage(), messageAdjustDTO.getRows()).getData();
        int count = messageListVoPageData.getTotalCount();
        List<MessageListInfoVo> messageListInfoVos = messageListVoPageData.getData();
        if(0 < count){
            List<Long> messageIdList = messageListInfoVos.stream().map(MessageListInfoVo::getId).collect(Collectors.toList());
            MessageListDTO messageList = new MessageListDTO();
            messageList.setMessageIds(messageIdList);
            //将点击的消息类型的所有消息的状态改为已读
            messageServiceFeign.messageAdjust(messageList);
        }
        //匠心放入
        List<MessageListVo> messageListVo = new ArrayList<>();
        MessageListVo messageListVoList = new MessageListVo();
        messageListVoList.setMessageList(messageListInfoVos);
        if(3 == messageAdjustDTO.getType()){
            messageListVoList.setSystemUnreadCount(0);
        }
        if(2 == messageAdjustDTO.getType()){
            messageListVoList.setReviewUnreadCount(0);
        }
        if(1 == messageAdjustDTO.getType()){
            messageListVoList.setCommentUnreadCount(0);
        }
        messageListVo.add(messageListVoList);

        return Wrapper.success(messageListVo, count);
    }
}
