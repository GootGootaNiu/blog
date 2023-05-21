package com.king.blog.controller;

import com.king.blog.service.CategoryService;
import com.king.blog.vo.CategoryVo;
import com.king.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("categorys")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部信息
     * @return
     */
    @GetMapping
    public Result selectAll() {
        // 查询全部
        return categoryService.selectAll();
    }

    /**
     * 查询所有文章分类
     * @return
     */
    @GetMapping("/detail")
    public Result detail(){
        // 查询所有文章分类
        return categoryService.selectAllDetail();
    }

    @GetMapping("/detail/{id}")
    public Result findAllById(@PathVariable("id")Long id){
        return categoryService.selectfindById(id);
    }

}
