package com.springboot.blog.demo.service.impl;

import com.springboot.blog.demo.entity.Post;
import com.springboot.blog.demo.exception.ResourceNotFoundException;
import com.springboot.blog.demo.payload.PostDto;
import com.springboot.blog.demo.payload.PostResponse;
import com.springboot.blog.demo.repository.PostRepository;
import com.springboot.blog.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert TO to entity

        Post post = maptoEntity(postDto);
        Post newPost = postRepository.save(post);

      // convert entity to DTO
//        PostDto postResponse = mapToDTO(newPost);
        return postDto;
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setModifiedAt(post.getModifiedAt());
        postDto.setDeletedFlag(post.getDeletedFlag());

        return postDto;
    }

    private Post maptoEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setModifiedAt(post.getModifiedAt());
        postDto.setDeletedFlag(post.getDeletedFlag());
        return post;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content =  listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostbyId(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setModifiedAt(new Date());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }



    @Override
    public void delelePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id));
        post.setDeletedFlag(0);
        Post deletePost =  postRepository.save(post);
    }

    @Override
    public List<Post> getPostByIdWithDeleteFlag() {

        return postRepository.findByDeletedFlag();
    }



}
