package com.springboot.blog.demo.payload;


import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name can not be empty or null")
    private String name;

    @NotEmpty(message = "Name can not be empty or null")
    @Email
    private String email;

    @NotEmpty(message = "Name can not be empty or null")
    @Size(min = 10, message="comment must have a least 10 characters")
    private String body;
    protected Date createdAt;
    protected Date modifiedAt;
    protected Integer deletedFlag;
}
