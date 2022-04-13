package com.microservice.productsservice.Controllers;

import com.microservice.productsservice.DTOs.BookDTO;
import com.microservice.productsservice.Entities.Author;
import com.microservice.productsservice.Entities.Book;
import com.microservice.productsservice.Entities.Category;
import com.microservice.productsservice.Repositories.AuthorRepository;
import com.microservice.productsservice.Repositories.CategoryRepository;
import com.microservice.productsservice.Services.AuthorService;
import com.microservice.productsservice.Services.BookService;
import com.microservice.productsservice.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product-service")
@RequiredArgsConstructor
public class ProductController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllProduct(){
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getProductById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createProduct(@RequestBody BookDTO book){
        return bookService.createBook(book);
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> searchBook(@RequestParam("query") String query){
        return bookService.searchBook(query);
    }
}
