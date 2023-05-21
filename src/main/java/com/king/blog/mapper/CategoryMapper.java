package com.king.blog.mapper;

import com.king.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询全部
     * @return
     */
    List<Category> selectAll();

    /**
     * 根据id 查询数据
     * select * from ms_article where category_id =1;
     * @param categoryId
     * @return
     */
    Category selectByCategoryId(@Param("id") Long categoryId);

    /**
     * 根据id 查询文章数据
     * @param id
     * @return
     */
    Category selectAllById(Long id);
}
