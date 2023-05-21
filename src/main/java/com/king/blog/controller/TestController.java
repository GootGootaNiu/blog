package com.king.blog.controller;

import com.king.blog.pojo.SysUser;
import com.king.blog.utils.UserThreadLocal;
import com.king.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public Result test(){
        // 使用
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
















