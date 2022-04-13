package com.microservice.productsservice.Services;

import com.microservice.productsservice.Entities.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryByName(String name);
    public Category getCategoryById(Long id);
    public Category create(Category category);
}
