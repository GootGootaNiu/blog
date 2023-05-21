package com.king.blog.controller;

import com.king.blog.service.TagService;
import com.king.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("hot")
    public Result hot(){
        int limit = 6;
        return tagService.hots(limit);
    }

    /**
     *  查询标签
     * @return
     */
    @GetMapping
    public Result selectAll(){
        return tagService.selectAll();
    }

    @GetMapping("detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    /**
     * 根据标签id 查询数据
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public Result findAllDetailById(@PathVariable("id") Long id){
        return tagService.findAllDetailById(id);
    }
}
