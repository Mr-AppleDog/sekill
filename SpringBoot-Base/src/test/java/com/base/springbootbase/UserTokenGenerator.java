package com.base.springbootbase;

import com.alibaba.fastjson.JSONObject;
import com.base.springbootbase.common.core.domain.entity.SysUser;
import com.base.springbootbase.common.util.SecurityUtils;
import com.base.springbootbase.service.Impl.SysUserServiceImpl;
import com.base.springbootbase.service.SysUserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.aspectj.lang.annotation.AfterReturning;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/31 16:33
 */
@SpringBootTest
public class UserTokenGenerator {
    // 用户名前缀
    private static final String USERNAME_PREFIX = "user_";
    // 登录地址
    private static final String LOGIN_URL = "http://localhost:9090/login";
    // 生成的token保存的文件路径
    private static final String TOKEN_FILE_PATH = System.getProperty("user.home") + "\\Desktop\\tokens.txt";
    // 要生成的用户数量
    private static final int USER_COUNT = 100;
    // HTTP客户端超时时间(秒)
    private static final int HTTP_TIMEOUT = 10;
    @Autowired
    private SysUserService sysUserService;
    /**
     * 生成指定数量的用户
     * @param index 用户数量user_index
     * @param end 用户数量user_end
     * @return 用户列表
     */
    public  List<SysUser>  generateUsers(int index, int end) {
        List<SysUser> users = new ArrayList<>();
        for (int i = index; i < end; i++) {
            SysUser user = new SysUser();
            String username = USERNAME_PREFIX + i;
            // 生成随机6位数字密码，符合示例中的数字密码格式
            String password = "123456";
            user.setUserName(username);
            user.setPassword(password);
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
            users.add(user);
            sysUserService.insertUser(user);
        }
        int count = end - index;
        System.out.println("成功生成 " + count + " 个用户");
        return users;
    }

    /**
     * 通过HTTP请求登录并获取token
     * @param user 用户信息
     * @return 登录成功返回token，失败返回null
     */
    public static Map<String, String> login(SysUser user) {
        // 创建HTTP客户端
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(HTTP_TIMEOUT))
                .build();

        // 构建JSON格式的请求体 - 注意密码字段添加引号
        String jsonBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}",
                user.getUserName(), "123456");

        // 构建HTTP请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            // 检查响应状态码
            if (response.statusCode() == 200) {
                // 解析JSON响应
//                JsonNode jsonNode = objecdtMapper.readTree(response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String token = jsonObject.getString("data");
                // 获取data字段的值（即token）
                // 格式化Bearer token（前缀+token）
                String bearerToken = "Bearer " + token;
                Map<String, String> result = new HashMap<>();
                result.put("username", user.getUserName());
                result.put("token", bearerToken);
                System.out.println("用户 " + user.getUserName() + " 登录成功，Bearer token: " + bearerToken);
                return result;
            } else {
                System.err.println("用户 " + user.getUserName() + " 登录失败，状态码: " + response.statusCode() +
                        "，响应: " + response.body());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("用户 " + user.getUserName() + " 登录请求异常: " + e.getMessage());
            Thread.currentThread().interrupt();
            return null;
        }
    }
    /**
     * 批量登录用户（并行处理提高效率）
     * @param users 用户列表
     * @return token列表
     */
    public  List<Map<String, String>> batchLogin(List<SysUser> users) {
        List<Map<String, String>> userTokens = new ArrayList<>();
        List<CompletableFuture<Map<String, String>>> futures = new ArrayList<>();
        // 为每个用户创建登录异步任务
        for (SysUser user : users) {
            CompletableFuture<Map<String, String>> future = CompletableFuture.supplyAsync(() -> login(user));
            futures.add(future);
        }

        // 等待所有任务完成并收集结果
        for (CompletableFuture<Map<String, String>> future : futures) {
            try {
                Map<String, String> userToken = future.get();
                if (userToken != null) {
                    userTokens.add(userToken);
                }
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("处理登录结果时发生异常: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        return userTokens;
    }
    /**
     * 将token列表保存到文件
     * @param userTokens token列表
     * @param filePath 文件路径
     * @return 保存成功返回true，否则返回false
     */
    public  boolean saveTokensToFile(List<Map<String, String>> userTokens, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map<String, String> userToken : userTokens) {
                String line = userToken.get("username") + ", " + userToken.get("token");
                writer.write(line);
                writer.newLine();
            }
            System.out.println("成功将 " + userTokens.size() + " 条用户token信息保存到文件: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("保存token到文件失败: " + e.getMessage());
            return false;
        }
    }

    @Test
    public  void te() {
        //生成用户
//        List<SysUser> sysUsers = generateUsers(10,10000);
        // 从数据库读取用户
        SysUser user=new SysUser();
        user.setUserName(USERNAME_PREFIX);
        List<SysUser> sysUsers  = sysUserService.selectUserList(user);
        System.out.println(sysUsers);
        // 批量登录并收集token（使用并行处理提高效率）
        System.out.println("开始批量登录用户...");
        List<Map<String, String>> userTokens = batchLogin(sysUsers);
//
        // 将token保存到文件
        if (!userTokens.isEmpty()) {
            saveTokensToFile(userTokens, TOKEN_FILE_PATH);
        } else {
            System.out.println("没有获取到有效的用户token信息，不保存文件");
        }
    }
}
