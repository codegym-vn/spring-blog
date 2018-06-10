package com.codegym.blog.controller;

import com.codegym.blog.model.Post;
import com.codegym.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping(value = {"", "/"})
    public ModelAndView index(Pageable pageable){
        Page<Post> posts = postService.findAllSummary(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
}
