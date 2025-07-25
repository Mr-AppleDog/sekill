package com.base.springbootbase.common.exception;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/24 23:54
 */
public class PasswordErrorException extends BaseException{
    public PasswordErrorException() {
    }
    public PasswordErrorException(String message) {
        super(message);
    }
}
