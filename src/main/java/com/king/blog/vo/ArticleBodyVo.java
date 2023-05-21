package com.king.blog.vo;

import lombok.Data;

@Data
public class ArticleBodyVo {

    /**
     * 文章内容
     *  根据id 查询文章的内容 前端需要返回一个 文章内容
     */
    private String content;
}
