package com.springboot.blog.demo.service.impl;

import com.springboot.blog.demo.entity.Comment;
import com.springboot.blog.demo.entity.Post;
import com.springboot.blog.demo.exception.BlogAPIException;
import com.springboot.blog.demo.exception.ResourceNotFoundException;
import com.springboot.blog.demo.payload.CommentDto;
import com.springboot.blog.demo.repository.CommentRepository;
import com.springboot.blog.demo.repository.PostRepository;
import com.springboot.blog.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    /**
     *
     * create comment in a post
     * @param postId
     * @param commentDto
     * @return
     */

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }


    /**
     *
     * get comment by post id
     * @param postId
     * @return
     */

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

       List<Comment> comments = commentRepository.findByPostId(postId);

       return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {


        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResourceNotFoundException("Comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, long commentId, CommentDto commentRequest) {

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResourceNotFoundException("Comment","id",commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        comment.setModifiedAt(new Date());

        Comment updateComment = commentRepository.save(comment);
        return mapToDto(updateComment);


    }

    @Override
    public void deleteComment(Long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResourceNotFoundException("Comment","id",commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
        }

        commentRepository.delete(comment);



    }

    @Override
    public void delelePostById(long id) {

    }


    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setModifiedAt(comment.getModifiedAt());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setDeletedFlag(comment.getDeletedFlag());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
//        comment.setCreatedAt(commentDto.getCreatedAt());
//        comment.setModifiedAt(commentDto.getModifiedAt());
//        comment.setDeletedFlag(commentDto.getDeletedFlag());
        return comment;
    }


}
