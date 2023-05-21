package com.king.blog.mapper;

import com.king.blog.pojo.Article;
import com.king.blog.pojo.SysUser;
import com.king.blog.vo.ArticleVo;
import com.king.blog.vo.dos.Archives;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 查询全部数据
     *
     * @return
     */
    List<Article> selectAll();

    /**
     * 分页查询首页
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Article> selectPage(int page, int pageSize);

    //String year,
//                               String month
    List<Article> selectPages(int page, int pageSize, Long categoryId, Long tagId, String year, String month);

    /**
     * limit 根据limit 查询数据
     *
     * @return
     */
    List<Article> selectsLimit(int limit);

    /**
     * 根据authorId 查询数据
     *
     * @param authorId
     * @return
     */
    List<Article> findById(Long authorId);


    /**
     * 最新文章查询
     *
     * @param limit
     * @return
     */
    List<Article> selectsLimits(int limit);

    /**
     * 文章归档
     *
     * @return
     */
    List<Archives> listArchives();

    /**
     * 根据用户的id 查询数据
     *
     * @param id
     * @return
     */
    Article selectById(Long id);


    /**
     * 用户浏览量添加
     *
     * @param viewCounts1
     * @param viewCounts
     * @param id
     * @return
     */
    Integer updateArticle(Integer viewCounts1, Integer viewCounts, Long id);

    /**
     * 插入文章信息
     *
     * @param article
     */
    void insert(Article article);

    /**
     * 更新Article 中的数据
     *
     * @param article
     */
    void updateById(Article article);


}

