package com.readingisgood.service;

import com.readingisgood.model.Book;

public interface BookService {

    Book save(Book book);

    Book findById(Long id);
}
