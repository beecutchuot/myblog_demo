package com.springboot.blog.demo.repository;

import com.springboot.blog.demo.entity.Post;
import com.springboot.blog.demo.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    @Query(nativeQuery = true, value = "select * from posts where posts.deleted_flag = 1")
    List<Post> findByDeletedFlag();
}
