package com.king.blog.utils;

import com.king.blog.pojo.SysUser;

/**
 * 创建线程
 */
public class UserThreadLocal {
    private UserThreadLocal(){}
    private static final  ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    // put 添加方法
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    // 获取元素
    public static SysUser get(){
        return LOCAL.get();
    }

    // 删除元素
    public static void  remove(){
        LOCAL.remove();
    }

}
