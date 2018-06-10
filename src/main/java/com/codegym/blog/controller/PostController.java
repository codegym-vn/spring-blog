package com.codegym.blog.controller;

import com.codegym.blog.model.Category;
import com.codegym.blog.model.Post;
import com.codegym.blog.model.PostForm;
import com.codegym.blog.service.CategoryService;
import com.codegym.blog.service.PostService;
import com.codegym.blog.utils.StorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView findAll(Pageable pageable){
        Page<Post> posts = postService.findAllSummary(pageable);
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id, Pageable pageable){
        Post post = postService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/post/view");
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreationForm(){
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("postForm", new PostForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView savePost(@ModelAttribute("postForm") PostForm postForm ){
        try {
            String randomCode = UUID.randomUUID().toString();
            String originFileName = postForm.getFeature().getOriginalFilename();
            String randomName = randomCode + StorageUtils.getFileExtension(originFileName);
            postForm.getFeature().transferTo(new File(StorageUtils.FEATURE_LOCATION + "/" + randomName));
            Post post = new Post();
            post.setTitle(postForm.getTitle());
            post.setTeaser(postForm.getTeaser());
            post.setContent(postForm.getContent());
            post.setCreated(LocalDate.now());
            post.setFeature(randomName);
            post.setCategory(postForm.getCategory());
            postService.save(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/post/create");
        modelAndView.addObject("postForm", new PostForm());
        modelAndView.addObject("message", "Post saved successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Post post = postService.findById(id);
        PostForm postForm = new PostForm();
        postForm.setId(post.getId());
        postForm.setTitle(post.getTitle());
        postForm.setTeaser(post.getTeaser());
        postForm.setContent(post.getContent());
        postForm.setCategory(post.getCategory());
        postForm.setFeatureUrl(post.getFeature());

        ModelAndView modelAndView = new ModelAndView("/post/edit");
        modelAndView.addObject("postForm", postForm);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updatePost(@PathVariable("id") Long id, @ModelAttribute("postForm") PostForm postForm){
        try {
            Post post = postService.findById(id);
            if(!postForm.getFeature().isEmpty()){
                StorageUtils.removeFeature(post.getFeature());
                String randomCode = UUID.randomUUID().toString();
                String originFileName = postForm.getFeature().getOriginalFilename();
                String randomName = randomCode + StorageUtils.getFileExtension(originFileName);
                postForm.getFeature().transferTo(new File(StorageUtils.FEATURE_LOCATION + "/" + randomName));
                post.setFeature(randomName);
                postForm.setFeatureUrl(randomName);
            }
            post.setId(postForm.getId());
            post.setTitle(postForm.getTitle());
            post.setTeaser(postForm.getTeaser());
            post.setContent(postForm.getContent());
            post.setCategory(postForm.getCategory());
            postService.save(post);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView("/post/edit");
        modelAndView.addObject("postForm", postForm);
        modelAndView.addObject("message", "Post updated successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeletionForm(@PathVariable("id") Long id){
        Post post = postService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable("id")Long id){
        Post post = postService.findById(id);
        StorageUtils.removeFeature(post.getFeature());
        postService.delete(id);
        return "redirect:/posts";
    }
}
