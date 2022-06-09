package com.springboot.blog.demo.controller;

import com.springboot.blog.demo.entity.Post;
import com.springboot.blog.demo.payload.PostDto;
import com.springboot.blog.demo.payload.PostResponse;
import com.springboot.blog.demo.service.PostService;
import com.springboot.blog.demo.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/demo")
public class PostController {

    private PostService postService;

    // tiem phu thuoc bang constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create a new post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    /**
     * get all post
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEDFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEDFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEDFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEDFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        log.info("start -------method : getAllPosts -----------");
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);

    }

    //get  posst by id
    @GetMapping("get-by/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostbyId(id));
    }


    /**
     * get all post with deleteflag =1
     *
     * @return
     */

    @GetMapping("get-all-with-deleteflag")
    public ResponseEntity<List<Post>> getAllPostWithDeleteFlag() {
        return ResponseEntity.ok(postService.getPostByIdWithDeleteFlag());
    }


    /**
     * update a post
     * @param postDto
     * @param id
     * @return
     */


    @PutMapping("update/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name ="id") long id) {
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name ="id") long id) {
        postService.delelePostById(id);
        return new ResponseEntity<>("deleted ok",HttpStatus.OK);

    }


}
