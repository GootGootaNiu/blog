package com.king.blog.handler;

import com.king.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 对加了 @Controller 注解的方法进行拦截处理 AOP 的实现
@ControllerAdvice
public class AllExceptionHandler {
    // 进行异常处理 处理Exception.class 的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回JSON数据
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}

























