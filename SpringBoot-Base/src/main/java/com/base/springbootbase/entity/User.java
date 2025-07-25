package com.base.springbootbase.entity;

import lombok.Data;

/**
 * @author MrLu
 * @version 1.0
 * @description:
 * @date 2025/7/24 17:56
 */
@Data
public class User  {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
