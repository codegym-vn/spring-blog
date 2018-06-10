package com.codegym.blog.controller.api;

import com.codegym.blog.model.Category;
import com.codegym.blog.model.Post;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(value = "*")
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> findAll(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable("id") Long id, Pageable pageable){
        Category category = categoryService.findById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<Page<Post>> findPosts(@PathVariable("id") Long id, Pageable pageable){
        Category category = categoryService.findById(id);
        Page<Post> posts = categoryService.findPosts(category, pageable);
        return new ResponseEntity<Page<Post>>(posts, HttpStatus.OK);
    }
}
