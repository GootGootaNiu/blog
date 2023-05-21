package com.king.blog.service.impl;

import com.king.blog.mapper.ArticleBodyMapper;
import com.king.blog.mapper.CategoryMapper;
import com.king.blog.pojo.ArticleBody;
import com.king.blog.pojo.Category;
import com.king.blog.service.CategoryService;
import com.king.blog.vo.CategoryVo;
import com.king.blog.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param categoryId
     * @return
     */
    @Override
    public CategoryVo selectById(Long categoryId) {
        Category category = categoryMapper.selectByCategoryId(categoryId);
        CategoryVo categoryVo = new CategoryVo();
//        List<CategoryVo> categoryVo = new ArrayList<>();
//        categoryVo.setId(category.getId());
//        categoryVo.setAvatar(category.getAvatar());
//        categoryVo.setCategoryName(category.getCategoryName());
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo ;
    }

    /**
     * 分类查询
     * @return
     */
    @Override
    public Result selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        return Result.success(copyList(categories));
    }

    /**
     * 查询所有的分类信息
     * @return
     */
    @Override
    public Result selectAllDetail() {
        List<Category> categories = categoryMapper.selectAll();
        return Result.success(copyList(categories));
    }

    /**
     * 根据分类id 查询数据
     * @return
     */
    @Override
    public Result selectfindById(Long id) {
        Category category = categoryMapper.selectAllById(id);
        return Result.success(copy(category));
    }

    /**
     * vo类
     * @param categories
     * @return
     */
    private List<CategoryVo> copyList(List<Category> categories) {
        List<CategoryVo> categoryVos = new ArrayList<>();
        // 使用for 循环把 Categor 中的数据 遍历到Vo 类中去
        for (Category category : categories){
            categoryVos.add(copy(category));
        }
        return categoryVos;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}

























