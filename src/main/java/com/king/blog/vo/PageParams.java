package com.king.blog.vo;

import lombok.Data;

/**
 * 分页查询
 */
@Data
public class PageParams {
    // 当前页码
    private int page = 1;
    // 每次分几页
    private int pageSize =10;
    private Long categoryId;
    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}



















