package com.king.blog.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.king.blog.common.aop.LogAnnotation;
import com.king.blog.pojo.Article;
import com.king.blog.service.ArticleService;
import com.king.blog.vo.ArticleVo;
import com.king.blog.vo.PageParams;
import com.king.blog.vo.Result;
import com.king.blog.vo.params.ArticleParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController extends ArticleVo {

    @Autowired
    private ArticleService articleService;

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }

    /**
     * 根据作者的id 查询
     * @param articleId
     * @return
     */
//    @PostMapping("view/{id}")
    public Result listViewId(@PathVariable("id") Long articleId) {
        // 根据id 查询书籍的数据
        // 把查询到的数据封装到VO类中去
        ArticleVo articleVo = articleService.findArticleById(articleId);
        return Result.success(articleVo);
    }

    @PostMapping("view/{id}")
//    @Cache(expire = 5 * 60 * 1000,name = "view_article")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleByIds(articleId);
    }


    /**
     * 首页文章列表查询
     * @param pageParams
     * @return
     */
    // 使用自定义注解记录日志信息
    @LogAnnotation(module = "文章",operation = "获取文章列表")
    @PostMapping()
    public Result listArticle(@RequestBody PageParams pageParams) {
        List<ArticleVo> articleVoList = articleService.selectPages(pageParams);
        return Result.success(articleVoList);
    }

    /**
     * 最热文章查询
     *
     * @return
     */
    @PostMapping("hot")
    public Result listLimit() {
        int limit = 5;
        return articleService.selectLimit(limit);
    }

    /**
     * 最新文章查询
     *
     * @return
     */
    @PostMapping("new")
    public Result listLimits() {
        int limit = 5;
        return articleService.selectLimits(limit);
    }

    /**
     * 文章归档
     *
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

}



































