package com.king.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 封装数据 给前端
 * | 参数名称  | 参数类型 | 说明           |
 * | --------- | -------- | -------------- |
 * | articleId | long     | 文章id         |
 * | content   | string   | 评论内容       |
 * | parent    | long     | 父评论id       |
 * | toUserId  | long     | 被评论的用户id |
 */
@Data
public class CommentParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId; // 文章id
    private String content; // 评论内容
    private Long parent; // 父评论id
    private Long toUserId; // 被评论的用户id
}
