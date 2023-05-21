package com.king.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.king.blog.vo.ArticleVo;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Article {

    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title; // 标题

    private String summary; // 简介

    private Integer commentCounts; //评论数量

    private Integer viewCounts; // 浏览数量

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private Integer weight;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long createDate;

    /**
     * 创建时间
     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createDate;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createDate;

//    private String createDate;
}
