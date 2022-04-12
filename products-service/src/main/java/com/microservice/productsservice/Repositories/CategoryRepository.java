package com.microservice.productsservice.Repositories;

import com.microservice.productsservice.Entities.Book;
import com.microservice.productsservice.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Optional<Category> findByName(String name);
}
