package com.king.blog.service;

import com.king.blog.pojo.ArticleBody;
import com.king.blog.vo.ArticleBodyVo;

public interface ArticleBodyService {

    /**
     * 根据BodyId查询数据
     *
     * @param bodyId
     * @return
     */
    ArticleBodyVo selectBodyId(Long bodyId);
}


























