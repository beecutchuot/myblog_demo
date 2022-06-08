package com.springboot.blog.demo.payload;


import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
    protected Date createdAt;
    protected Date modifiedAt;

}
