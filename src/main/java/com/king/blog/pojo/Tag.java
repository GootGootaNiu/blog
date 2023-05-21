package com.king.blog.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 文章中的作者 和 名字
 */
@Data
public class Tag {
    private Long id;

    private String avatar;

    private String tagName;

}
