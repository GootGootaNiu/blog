package com.king.blog.mapper;

import com.king.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * 查询全部数据
     * @return
     */
    List<Tag> selectAll();

    /**
     * 根据文章id 查询标签列表
     * @param articleId 文章id
     * @return 返回查询数据
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 返回
     * @param limit
     * @return
     */
    List<Long> findAllByTagIdLimIt(int limit);

    /**
     * 根据多个id 查询热门
     * @param tagIds
     * @return
     */
    List<Tag> findAllByTagIds(List<Long> tagIds);

    /**
     * 根据标签id 查询数据
     * @param id
     * @return
     */
    Tag findAllDetailById(Long id);
}





















