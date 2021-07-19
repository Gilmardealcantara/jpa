package br.com.alura.store.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book extends Product {
    private String author;
    private String numberOfPages;

    public Book(String name, String description, BigDecimal price, Category category, String author, String numberOfPages) {
        super(name, description, price, category);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book() {
    }

    public Book(String author, String numberOfPages) {
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
