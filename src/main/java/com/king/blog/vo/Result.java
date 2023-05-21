package com.king.blog.vo;

import lombok.Data;

@Data
public class Result {
    // 成功
    private boolean success;
    // 状态信息
    private  int code;
    // 错误信息
    private String msg;
    // 返回前端数据
    private Object data;

    // 查询成功返回给前端数据
    public static Result success(Object data){
        return new Result(true, 200,"success",data);
    }

    // 查询失败给用户一个提示信息
    public static Result fail(int code,String msg){
        return new Result(false,code,msg,null);
    }



    public Result(boolean success, int code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }





}































