package com.king.blog.vo.params;

import lombok.Data;

/**
 * 用户登录 接收两个参数
 */
@Data
public class LoginParam {
    // 用户账户
    private String account;
    // 用户密码
    private String password;
    // 发布名称
    private String nickname;
}
