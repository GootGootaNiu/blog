package com.king.blog.controller;

import com.king.blog.service.SysUserService;
import com.king.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     *
     * @param token
     * @return
     */
    @GetMapping ("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return  sysUserService.findUserByToken(token);
    }
}

























