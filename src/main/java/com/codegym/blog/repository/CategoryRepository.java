package com.codegym.blog.repository;

import com.codegym.blog.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
