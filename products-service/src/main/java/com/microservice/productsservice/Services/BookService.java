package com.microservice.productsservice.Services;

import com.microservice.productsservice.DTOs.BookDTO;
import com.microservice.productsservice.Entities.Book;
import com.microservice.productsservice.Repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService{
    public List<Book> getAllBook();
    public BookDTO getBookByName(String name);
    public List<Book> getBookByCategory(String category);
    public List<Book> getBookBySku(String sku);
    public BookDTO getBookById(Long id);
    public BookDTO createBook(BookDTO book);
    public List<Book> searchBook(String query);
}
