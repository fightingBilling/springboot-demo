package org.wxd.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by wxd on 16-9-25.
 * 定义RestControllerAdvice: 当RestController抛出异常时返回固定的错误消息
 * 如果定义了两个异常处理切面，且其中一个异常是另一个父类，则异常会被父类的切面捕获到，并且返回父类切面指定的返回值
 */

@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 这个切面不会被触发，因为已经有一个父类异常的切面
     * @param illegal
     * @return
     */
    @ExceptionHandler(value=IllegalArgumentException.class)
    public Object handleException(IllegalArgumentException illegal){
        System.out.println("IllegalArgumentException!!! it's too bad!!!");
        return "IllegalArgumentException!!! it's too bad!!!";
    }


    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e){
        System.out.println("this is root exception, any exception will be catched by this advice, other advice will be ignore");
        return e.getMessage();
    }
}
