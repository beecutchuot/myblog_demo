package com.springboot.blog.demo.service;

import com.springboot.blog.demo.entity.Comment;
import com.springboot.blog.demo.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateCommentById(Long postId, long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, long commentId);


}
