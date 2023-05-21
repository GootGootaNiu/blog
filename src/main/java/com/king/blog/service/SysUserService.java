package com.king.blog.service;

import com.king.blog.pojo.SysUser;
import com.king.blog.vo.Result;
import com.king.blog.vo.UserVo;

import java.util.List;

public interface SysUserService {
    /**
     * 根据用户的id 查询数据
     * @param authorId
     * @return
     */
    SysUser fingUserById(Long authorId);

    /**
     * 根据账户和密码 查询一条记录
     * @param account 账户
     * @param password 密码
     * @return
     */
    SysUser findUser(String account, String password);

    /**
     * 根据token 查询数据
     * @param token token 查询数据
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据authorId 查询数据
     * @param authorId
     * @return
     */
//    UserVo findUserByAuthorId(Long authorId);

    UserVo findUserByUserById(Long authorId);
}
































