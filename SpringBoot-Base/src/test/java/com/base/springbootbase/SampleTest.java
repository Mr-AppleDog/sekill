package com.base.springbootbase;

import com.base.springbootbase.common.util.RedisCache;
import com.base.springbootbase.domain.entity.User;
import com.base.springbootbase.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/24 18:02
 */
@SpringBootTest
public class SampleTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }
    @Test
    public void testPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
    @Autowired
    private RedisCache redisCache;
    @Test
    public void testRedis() {
        String userKey = "login_tokens:2be51598-65f4-4ba3-b529-85c2590e5f76";
        Object cacheObject = redisCache.getCacheObject(userKey);
        System.out.println(cacheObject);
    }

}
