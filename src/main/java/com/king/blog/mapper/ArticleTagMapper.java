package com.king.blog.mapper;

import com.king.blog.pojo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTagMapper {
    //文章关联

    Integer insert(ArticleTag articleTag);

    /**
     * 根据tagId 查询标签中的数据
     * @param tagId
     * @return
     */
    List<ArticleTag> selectAllById(Long tagId);
}
