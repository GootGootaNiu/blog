package com.king.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 文章详情展示
 */
@Data
public class ArticleBody {

    private Long id;
    private String content;
    private String contentHtml;

    private Long articleId;
}
