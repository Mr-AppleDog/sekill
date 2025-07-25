package com.base.springbootbase.common.exception;

/**
 * @author MrLu
 * @version 1.0
 * @description: 基础异常
 * @date 2025/7/24 23:49
 */
public class BaseException extends RuntimeException{
    public BaseException()
    {
    }
    public BaseException(String message)
    {
        super(message);
    }
}
