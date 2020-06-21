package com.lin.dao;

import com.lin.dto.BookCollectDTO;
import com.lin.dto.BookCollectUpdateDTO;
import com.lin.model.Collect;
import com.lin.vo.BookCollectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏表数据操作接口
 * @author hzh 2020-04-18
*/
@Mapper
@Repository
public interface CollectMapper extends BaseMapper<Collect, Long> {
    int searchBookCollectListCount(@Param("bookCollectDTO") BookCollectDTO bookCollectDTO);
    List<BookCollectVo> searchBookCollectList(@Param("bookCollectDTO") BookCollectDTO bookCollectDTO, @Param("page") int page,
                                              @Param("rows") int rows);
    Collect collectInfo(@Param("bookCollectUpdateDTO")BookCollectUpdateDTO bookCollectUpdateDTO);
}