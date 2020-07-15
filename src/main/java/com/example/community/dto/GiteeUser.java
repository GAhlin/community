package com.example.community.dto;

import lombok.Data;

/**
 * @Author Gavin
 * @Date 2020/7/15
 */
@Data
public class GiteeUser {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
