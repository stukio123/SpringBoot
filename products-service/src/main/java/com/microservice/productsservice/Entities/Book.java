package com.microservice.productsservice.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Books")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonManagedReference
    private List<Author> authors;

    private String sku;
    private String sumary;
    private String img;
    private BigDecimal price;
    private long stock;

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategory(Category category){
        this.getCategories().remove(category);
        category.getBooks().remove(this);
    }

    public void removeAuthor(Author author){
        this.getAuthors().remove(author);
        author.getBooks().remove(this);
    }

    public void removeAllCategories(){
        this.getCategories().clear();
    }

    public void removeAllAuthors(){
        this.getAuthors().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return stock == book.stock
                && Objects.equals(id, book.id)
                && Objects.equals(name, book.name)
                && Objects.equals(categories, book.categories)
                && Objects.equals(authors, book.authors)
                && Objects.equals(sku, book.sku)
                && Objects.equals(sumary, book.sumary)
                && Objects.equals(img, book.img)
                && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categories, authors, sku, sumary, img, price, stock);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", authors=" + authors +
                ", sku='" + sku + '\'' +
                ", sumary='" + sumary + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
