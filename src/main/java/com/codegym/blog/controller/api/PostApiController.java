package com.codegym.blog.controller.api;

import com.codegym.blog.model.Category;
import com.codegym.blog.model.Post;
import com.codegym.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(value = "*")
public class PostApiController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<Post>> findAll(Pageable pageable){
        Page<Post> posts = postService.findAllSummary(pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable("id") Long id, Pageable pageable){
        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
