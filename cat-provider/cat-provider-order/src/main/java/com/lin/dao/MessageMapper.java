package com.lin.dao;

import com.lin.dto.MessageListDTO;
import com.lin.model.Message;
import com.lin.vo.MessageListInfoVo;
import com.lin.vo.MessageListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息表数据操作接口
 * @author hzh 2020-03-20
*/
@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message, Long> {
    /**
     * 查看消息列表的数量
     * @param messageListDTO
     * @return 返回消息列表的数量
     */
    int searchMessageListCount(@Param("messageListDTO")MessageListDTO messageListDTO);

    /**
     * 查看消息列表
     * @param messageListDTO
     * @param page 页码
     * @param rows 行数
     * @return 返回消息列表
     */
    List<MessageListInfoVo> searchMessageList(@Param("messageListDTO")MessageListDTO messageListDTO, @Param("page") int page,
                                              @Param("rows") int rows);

    /**
     * 查看消息不同类型未读的数量
     * @param messageListDTO
     * @return 返回消息不通类型未读的数量
     */
    int searchMessageUnreadCount(@Param("messageListDTO")MessageListDTO messageListDTO);
}