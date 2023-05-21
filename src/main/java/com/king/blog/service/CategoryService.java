package com.king.blog.service;


import com.king.blog.vo.CategoryVo;
import com.king.blog.vo.Result;

import java.util.List;

public interface CategoryService {
    //select * from ms_article where category_id =1;
    CategoryVo selectById(Long categoryId);

    /**
     * 查询全部
     * @return
     */
    Result selectAll();

    /**
     * 查询所有的分类信息
     * @return
     */
    Result selectAllDetail();

    Result selectfindById(Long id);
}
