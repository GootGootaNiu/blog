package com.king.blog.service;

import com.king.blog.pojo.Tag;
import com.king.blog.vo.Result;
import com.king.blog.vo.TagVo;

import java.util.List;

public interface TagService {
    /**
     * 根据id 查询数据信息  多表查询
     * @param articleId 首页的id
     * @return 查询到的数据封装到Vo类中
     */
    List<TagVo> findTagByArticleId(Long articleId);

    /***
     * 最热标签拥有的文章数量最多
     * @param limit
     * @return
     */
    Result hots(int limit);

    /**
     * 查询全部标签
     * @return
     */
    Result selectAll();

    /**
     * 查询全部标签分类
     * @return
     */
    Result findAllDetail();

    /**
     * 根据id 查询标签分类列表
     * @param id
     * @return
     */
    Result findAllDetailById(Long id);
}












