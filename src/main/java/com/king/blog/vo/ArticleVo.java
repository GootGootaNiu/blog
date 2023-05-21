package com.king.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.king.blog.pojo.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

    /**
     * articleVo 这里做修改
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;
//    private UserVo author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

//    private CategoryVo category;
    private CategoryVo categoryVo;
//    @JsonSerialize(using = ToStringSerializer.class)
//    private String id;
//
//    private String title;
//
//    private String summary;
//
//    private Integer commentCounts;
//
//    private Integer viewCounts;
//
//    private Integer weight;
//    /**
//     * 创建时间
//     */
//    private String createDate;
//
////    private UserVo author;
//   private String author;
//    private ArticleBodyVo body;
//
//    private List<TagVo> tags;
//
//    private CategoryVo category;

//    @JsonSerialize(using = ToStringSerializer.class)
//    private Long id;
//
//    private String title;
//
//    private String summary;
//
//    private Integer commentCounts;
//
//    private Integer viewCounts;
//
//    private Integer weight;
//    /**
//     * 创建时间
//     */
//    private String createDate;
//
//    private String author;
//
//    private ArticleBodyVo body;
//
//    private List<TagVo> tags;

//    private List<CategoryVo> category;

}
