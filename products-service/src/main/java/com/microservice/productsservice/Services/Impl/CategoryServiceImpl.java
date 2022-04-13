package com.microservice.productsservice.Services.Impl;

import com.microservice.productsservice.Entities.Category;
import com.microservice.productsservice.Repositories.CategoryRepository;
import com.microservice.productsservice.Services.CategoryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(
                ()->new EntityNotFoundException());
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException());
    }

    @Override
    public Category create(Category category) {
        Category cate = getCategoryByName(category.getName());
        return categoryRepository.save(category);
    }
}
