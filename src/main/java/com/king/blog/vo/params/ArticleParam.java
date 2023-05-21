package com.king.blog.vo.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.king.blog.vo.CategoryVo;
import com.king.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * 文章发布 vo 类进行封装
 *  发布文章需要用到的表
 *
 */
@Data
public class ArticleParam {
    // id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    // ms_article_body 文章表进行封装
    private ArticleBodyParam body;
    // ms_category 对标签进行封住
    private CategoryVo category;
    // 文章概述 简介 ms_article
    private String summary;
    // 文章标签进行封装
//    private TagVo tagVo;
    // 注意文章标签
    private List<TagVo> tags;
    // 标题
    private String title;

    private String search;
}

































