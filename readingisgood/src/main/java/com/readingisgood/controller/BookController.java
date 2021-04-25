package com.readingisgood.controller;

import com.google.common.base.Preconditions;
import com.readingisgood.dto.BookDTO;
import com.readingisgood.enums.ResponseStatus;
import com.readingisgood.model.Book;
import com.readingisgood.response.ResponseModel;
import com.readingisgood.service.BookService;
import com.readingisgood.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book Controller")
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel<Book>> createBook(@RequestBody BookDTO bookDTO, @AuthenticationPrincipal User user) {
        ResponseEntity<ResponseModel<Book>> response = null;
        try {
            validateCreateBookInputs(bookDTO);

            Book book = bookDTO.getBookFromDTO(user);
            Book savedBook = bookService.save(book);
            ResponseModel<Book> successResponse = new ResponseModel<>(savedBook, ResponseStatus.SUCCESS, Constants.SAVED);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<Book> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<ResponseModel<Book>> getBookById(@RequestParam Long bookId) {
        ResponseEntity<ResponseModel<Book>> response = null;
        try {
            Preconditions.checkNotNull(bookId, "Book ID can not be null");

            Book book = bookService.findById(bookId);
            ResponseModel<Book> successResponse = new ResponseModel<>(book, ResponseStatus.SUCCESS, StringUtils.EMPTY);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<Book> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    private void validateCreateBookInputs(BookDTO bookDTO) {
        Preconditions.checkNotNull(bookDTO.getName(), "Name can not be null");
        Preconditions.checkArgument(bookDTO.getStock() != null && !(bookDTO.getStock().compareTo(0L) < 0), "Stock can not be null or negative");
    }

}
