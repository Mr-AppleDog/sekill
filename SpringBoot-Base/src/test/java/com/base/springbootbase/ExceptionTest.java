package com.base.springbootbase;

import com.base.springbootbase.common.constant.HttpStatus;
import com.base.springbootbase.common.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author MrLu
 * @version 1.0
 * @description: 异常测试类
 * @date 2025/7/25 0:14
 */
@SpringBootTest
public class ExceptionTest {

    @Test
    public void ServiceExceptionTest() {
        try {
            throw new ServiceException("获取部门ID异常", HttpStatus.UNAUTHORIZED);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
