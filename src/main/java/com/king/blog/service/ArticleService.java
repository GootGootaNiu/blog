package com.king.blog.service;

import com.king.blog.pojo.Article;
import com.king.blog.vo.ArticleVo;
import com.king.blog.vo.PageParams;
import com.king.blog.vo.Result;
import com.king.blog.vo.params.ArticleParam;

import java.util.List;

public interface ArticleService {

    /**
     * page 首页分页查询用户数据
     * @param pageParams
     * @return
     */
    List<ArticleVo> selectPages(PageParams pageParams);


    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result selectLimit(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result selectLimits(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 文章详情
     *   分析 这里有两张表做出查询的
     * @param articleId
     * @return
     */
    ArticleVo findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam 根据前端页码封装的数据
     * @return 返回给用户数据
     */
    Result publish(ArticleParam articleParam);

    /**
     * 文章详情
     * @param articleId
     * @return
     */
    Result findArticleByIds(Long articleId);
}


























