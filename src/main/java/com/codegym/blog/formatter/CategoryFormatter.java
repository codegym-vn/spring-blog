package com.codegym.blog.formatter;

import com.codegym.blog.model.Category;
import com.codegym.blog.service.CategoryService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {

    private CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Long id = Long.parseLong(text);
        return categoryService.findById(id);
    }

    @Override
    public String print(Category object, Locale locale) {
        return object.getName();
    }
}
