package com.microservice.productsservice.Services.Impl;

import com.microservice.productsservice.Entities.Author;
import com.microservice.productsservice.Repositories.AuthorRepository;
import com.microservice.productsservice.Services.AuthorService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name).orElseThrow(
                ()->new EntityNotFoundException());
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException());
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
