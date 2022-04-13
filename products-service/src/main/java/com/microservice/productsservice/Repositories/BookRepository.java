package com.microservice.productsservice.Repositories;

import com.microservice.productsservice.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public Optional<Book> findBookByName(String name);
    public List<Book> findBookBySku(String sku);

    @Query("SELECT DISTINCT b FROM Book b JOIN FETCH b.categories c WHERE c.name like %:name%")
    public List<Book> findBookByCategory(@Param("name") String name);

    @Query("SELECT b FROM Book b WHERE b.name LIKE CONCAT('%',:query,'%') " +
            "OR b.sumary LIKE CONCAT('%',:query,'%')")
    List<Book> searchBooks(String query);

}
