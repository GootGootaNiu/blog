package com.king.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 文章关联表
 */
@Data
public class ArticleTag {

    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;
    private Long tagId;
}
