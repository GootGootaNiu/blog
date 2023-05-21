package com.king.blog.controller;

import com.king.blog.service.LoginService;
import com.king.blog.vo.Result;
import com.king.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoinController {


    /**
     * 建立一个登录的service
     */
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        // 登录 验证用户 访问用户表 但是
        // 第一步：传入两个参数  需要封装一下
        return loginService.login(loginParam);
    }
}






























