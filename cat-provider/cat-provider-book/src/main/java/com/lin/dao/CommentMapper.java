package com.lin.dao;

import com.lin.dto.CommentInsetDTO;
import com.lin.dto.CommentListDTO;
import com.lin.model.Comment;
import com.lin.tools.Page;
import com.lin.vo.CommentListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论数据操作接口
 * @author hzh 2020-03-04
*/
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment, Long> {
    /**
     * 查看书籍评论数
     * @param commentListDTO
     * @return 返回书籍评论数
     */
    int searchCommentListCount(@Param("commentListDTO") CommentListDTO commentListDTO);

    /**
     * 查看书籍评论列表
     * @param commentListDTO
     * @param page
     * @return 返回书籍评论列表
     */
    List<CommentListVo> searchCommentList(@Param("commentListDTO") CommentListDTO commentListDTO, @Param("page") Page page);

}