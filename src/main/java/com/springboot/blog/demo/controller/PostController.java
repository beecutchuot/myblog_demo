package com.springboot.blog.demo.controller;

import com.springboot.blog.demo.payload.PostDto;
import com.springboot.blog.demo.payload.PostResponse;
import com.springboot.blog.demo.service.PostService;
import com.springboot.blog.demo.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    // tiem phu thuoc bang constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create a new post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all post
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEDFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEDFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEDFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEDFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    //get  posst by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostbyId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name ="id") long id){

        PostDto postResponse= postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.delelePostById(id);
        return  new ResponseEntity<>("delete successfully", HttpStatus.OK);

    }
}
