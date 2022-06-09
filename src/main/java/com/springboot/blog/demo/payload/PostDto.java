package com.springboot.blog.demo.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Data
public class PostDto {
    private long id;


    @NotEmpty
    @Size(min = 2, message = "Post title must have at least 2 chacrecters")
    private String title;

    @NotEmpty
    @Size(min = 20, message = "Post title must have at least 2 chacrecters")
    private String description;

    @NotEmpty
    private String content;

    protected Date createdAt;
    protected Date modifiedAt;
    protected Integer deletedFlag;
    private Set<CommentDto> comments;

}
