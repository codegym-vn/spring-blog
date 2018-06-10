package com.codegym.blog.repository;

import com.codegym.blog.model.Category;
import com.codegym.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @Query("select new Post(p.id, p.title, p.teaser, p.feature, p.created, p.likes, p.category) from Post p where p.category = :category")
    Page<Post> findByCategory(@Param("category") Category category, Pageable pageable);

    @Query("select new Post(p.id, p.title, p.teaser, p.feature, p.created, p.likes, p.category) from Post p")
    Page<Post> findAllSummary(Pageable pageable);
}
