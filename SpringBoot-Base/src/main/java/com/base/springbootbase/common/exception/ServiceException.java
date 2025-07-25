package com.base.springbootbase.common.exception;

/**
 * @author MrLu
 * @version 1.0
 * @description: 业务异常类
 * @date 2025/7/24 23:42
 */
public class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException()
    {
    }
    public ServiceException(String message)
    {
        this.message = message;
    }
    public ServiceException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }
    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

    public ServiceException setMessage(String message)
    {
        this.message = message;
        return this;
    }

}
