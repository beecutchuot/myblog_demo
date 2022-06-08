package com.springboot.blog.demo.payload;

import lombok.Data;

import java.util.Date;
import java.util.Iterator;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    protected Date createdAt;
    protected Date modifiedAt;
    protected Integer deletedFlag;

}
