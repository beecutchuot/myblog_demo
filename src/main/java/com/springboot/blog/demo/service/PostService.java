package com.springboot.blog.demo.service;

import com.springboot.blog.demo.payload.PostDto;
import com.springboot.blog.demo.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostbyId(long id);

    PostDto updatePost(PostDto postDto, long id);

   void delelePostById(long id);


}
