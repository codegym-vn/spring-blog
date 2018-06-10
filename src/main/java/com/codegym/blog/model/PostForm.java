package com.codegym.blog.model;

import org.springframework.web.multipart.MultipartFile;

public class PostForm {
    private Long id;
    private String title;
    private String teaser;
    private String content;
    private MultipartFile feature;
    private String featureUrl;
    private Category category;

    public PostForm() {
    }

    public PostForm(String title, String teaser, String content, MultipartFile feature, Category category) {
        this.title = title;
        this.teaser = teaser;
        this.content = content;
        this.feature = feature;
        this.category = category;
    }

    public PostForm(Long id, String title, String teaser, String content, MultipartFile feature, String featureUrl, Category category) {
        this.id = id;
        this.title = title;
        this.teaser = teaser;
        this.content = content;
        this.feature = feature;
        this.featureUrl = featureUrl;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFeature() {
        return feature;
    }

    public void setFeature(MultipartFile feature) {
        this.feature = feature;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFeatureUrl() {
        return featureUrl;
    }

    public void setFeatureUrl(String featureUrl) {
        this.featureUrl = featureUrl;
    }
}
