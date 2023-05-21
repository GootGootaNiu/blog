package com.king.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * vo 类
 */
@Data
public class CategoryVo {

    private Long id; // id
    private String avatar; // 图标路径
    private String categoryName; // 类别名称
}
