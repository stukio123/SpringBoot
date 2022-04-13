package com.microservice.productsservice.Services.Impl;

import com.microservice.productsservice.DTOs.BookDTO;
import com.microservice.productsservice.Entities.Author;
import com.microservice.productsservice.Entities.Book;
import com.microservice.productsservice.Entities.Category;
import com.microservice.productsservice.Repositories.AuthorRepository;
import com.microservice.productsservice.Repositories.BookRepository;
import com.microservice.productsservice.Repositories.CategoryRepository;
import com.microservice.productsservice.Services.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private AuthorRepository authorRepository;
    @Resource
    private CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
    @Override
    public BookDTO getBookByName(String name){
        return mapEntityToDto(bookRepository.findBookByName(name).orElseThrow(
                ()->new EntityNotFoundException("Úm bala xì bùa")));
    }
    @Override
    public List<Book> getBookByCategory(String category){
        return bookRepository.findBookByCategory(category);
    }
    @Override
    public List<Book> getBookBySku(String sku){
        return bookRepository.findBookBySku(sku);
    }
    @Override
    public BookDTO getBookById(Long id){
        return mapEntityToDto(bookRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException()));
    }
    @Override
    public BookDTO createBook(BookDTO book){
        Book entity = new Book();
        mapDtoTOEntity(book,entity);
        return mapEntityToDto(bookRepository.save(entity));
    }

    @Override
    public List<Book> searchBook(String query) {
        return bookRepository.searchBooks(query);
    }

    private void mapDtoTOEntity(BookDTO dto, Book entity){
        entity.setName(dto.getName());
        entity.setSumary(dto.getSumary());
        entity.setImg(dto.getImg());
        entity.setSku(dto.getSku());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        List<Author> entityAuthors = new ArrayList<>();
        List<Category> entityCategories = new ArrayList<>();
        if(null == entity.getAuthors()){
            entity.setAuthors(new ArrayList<>());
            dto.getAuthors().stream().forEach( e ->{
                Optional<Author> author = authorRepository.findByName(e);
                author.ifPresent(
                        (available)->{
                            entityAuthors.add(available);
                        });
            });
        }
        if(null == entity.getCategories()){
            entity.setCategories(new ArrayList<>());
            dto.getCategories().stream().forEach(e -> {
                Optional<Category> category = categoryRepository.findByName(e);
                category.ifPresent(
                        (available)->{
                            entityCategories.add(available);
                        });
            });
        }

        entity.setCategories(entityCategories);
        entity.setAuthors(entityAuthors);
    }

    private BookDTO mapEntityToDto( Book entity){
        if(null == entity){
            return null;
        }
        BookDTO newDTO = new BookDTO();
        newDTO.setId(entity.getId());
        newDTO.setName(entity.getName());
        newDTO.setImg(entity.getImg());
        newDTO.setSku(entity.getSku());
        newDTO.setPrice(entity.getPrice());
        newDTO.setStock(entity.getStock());
        newDTO.setSumary(entity.getSumary());
        Set<String> authors = new HashSet<>();
        entity.getAuthors().stream().forEach(e->{
            authors.add(e.getName());
        });
        newDTO.setAuthors(authors);
        Set<String> cates = new HashSet<>();
        entity.getCategories().stream().forEach(e->{
            cates.add(e.getName());
        });
        newDTO.setCategories(cates);
        return newDTO;
    }

}
