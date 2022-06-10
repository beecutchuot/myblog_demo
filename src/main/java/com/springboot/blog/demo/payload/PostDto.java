package com.springboot.blog.demo.payload;

import com.springboot.blog.demo.entity.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

}

