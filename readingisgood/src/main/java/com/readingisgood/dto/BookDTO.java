package com.readingisgood.dto;

import com.readingisgood.enums.Status;
import com.readingisgood.model.Book;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class BookDTO {

    private String name;
    private Long stock;

    public Book getBookFromDTO(User user) {
        Book book = new Book();
        book.setName(this.name);
        book.setStock(this.stock);
        book.setCreationDate(new Date());
        book.setStatus(Status.ACTIVE);
        book.setCreatedUser(user.getUsername());

        return book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
