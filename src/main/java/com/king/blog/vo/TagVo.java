package com.king.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 文章中的 作者和名字类
 */
@Data
public class TagVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
//    private String id;

    private String tagName;

    private String avatar;
}













