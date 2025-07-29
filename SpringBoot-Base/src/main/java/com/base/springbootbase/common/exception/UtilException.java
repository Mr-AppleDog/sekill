package com.base.springbootbase.common.exception;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 3:19
 */
public class UtilException  extends RuntimeException{
    public UtilException()
    {
    }

    public UtilException(String message)
    {
        super(message);
    }

}
