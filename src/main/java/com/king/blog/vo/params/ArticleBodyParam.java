package com.king.blog.vo.params;

import lombok.Data;

/**
 * 文章表进行封装
 */
@Data
public class ArticleBodyParam {
    // 文章内容
    private String content;
    // 文章标题
    private String contentHtml;
//    private Long articleId;
}
