package com.king.blog.mapper;

import com.king.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectAll();

    /**
     * 根据id 查询数据
     * @param articleId
     * @return
     */
    List<Comment> selectById(Long articleId);

    /**
     * 查询子评论
     * @param parentId
     * @return
     */
    List<Comment> selectByIds(Long parentId);

    /**
     * 添加评论数据
     * @param comment
     */
    void insert(Comment comment);
}
