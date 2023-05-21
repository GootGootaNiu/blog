package com.king.blog.service;

import com.king.blog.vo.CommentParam;
import com.king.blog.vo.Result;

public interface CommentService {
    /**
     * 根据文章id 查询所有的评论列表
     * @param id
     * @return
     */
    Result commentById(Long id);

    /**
     * 评论
     * @param commentParam 前端传过来的数据
     * @return
     */
    Result comment(CommentParam commentParam);
}
