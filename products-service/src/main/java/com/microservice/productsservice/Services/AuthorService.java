package com.microservice.productsservice.Services;

import com.microservice.productsservice.Entities.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> getAllAuthors();
    public Author getAuthorByName(String name);
    public Author getAuthorById(Long id);
    public Author create(Author author);
}
