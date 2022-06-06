package com.springboot.blog.demo.payload;


import lombok.Data;

import javax.persistence.Entity;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
