package com.king.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 评论列表
 */
@Data
public class Comment {

    // 评论id
    public static final long Article_TOP = 1;

    public static final long Article_Common = 0;

    private static final long serialVersionUID = 1L;

    private Long id;
    // 内容
    private String content;
    // 时间
    private Long createDate;
    // 评论id
    private Long articleId;
//    private Integer articleId;
    // 评论内容
    private Long authorId;
    // 对评论的评论进行回复
    private Long parentId;
    // 给谁评论
    private Long toUid;
    // 评论的第几层
    private Integer level;
}
