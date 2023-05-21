package com.king.blog.service;

import com.king.blog.pojo.SysUser;
import com.king.blog.vo.Result;
import com.king.blog.vo.params.LoginParam;

/**
 * 用户登录的 service
 *  它里面需要传入两个参数  但是这里我需要把两个参数给封装一下
 *
 */
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam 账户 和 密码
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     *
     * @return
     */
    SysUser checkToke(String token);

    /**
     * 退出登录删除 token
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册用户数据
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}






























