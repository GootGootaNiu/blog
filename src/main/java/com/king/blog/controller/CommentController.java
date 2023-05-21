package com.king.blog.controller;

import com.king.blog.service.CommentService;
import com.king.blog.vo.CommentParam;
import com.king.blog.vo.CommentVo;
import com.king.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long id){
        return commentService.commentById(id);
    }

    /**
     * 评论
     * @return
     */
    @PostMapping("/create/change")
    public Result CommentParams(@RequestBody CommentParam commentParam){
        // 接收前端传过来的数据
        return commentService.comment(commentParam);
    }

}
