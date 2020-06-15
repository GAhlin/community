package com.example.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentID;
    private String content;
    private Integer type;
}
