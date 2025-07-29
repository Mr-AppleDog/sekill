package com.base.springbootbase.common.util.spring;

import com.base.springbootbase.common.constant.CacheConstants;
import com.base.springbootbase.common.core.domain.entity.SysDictData;
import com.base.springbootbase.common.util.RedisCache;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/28 0:43
 */
public class SpringUtils {
    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory beanFactory;
    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException
    {
        return (T) beanFactory.getBean(name);
    }
    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }
    /**
     * 获取aop代理对象
     *
     * @param invoker
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker)
    {
        return (T) AopContext.currentProxy();
    }

}
