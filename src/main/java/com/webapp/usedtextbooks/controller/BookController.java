//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.controller;
import com.webapp.usedtextbooks.model.Book;
import com.webapp.usedtextbooks.repository.BookRepository;
import com.webapp.usedtextbooks.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @DeleteMapping("sell/id/{id}")
    public String sold_by_id(@PathVariable Long id) {
        if (bookService.sold_by_id(id))
            return "success";
        return "failure";
    }


    @PostMapping("/sell/isbn")
    public String UserSell(@RequestBody Book book) {
        if (bookService.UserSell(book))
            return "success";
        return "failure";
    }

    @GetMapping("/searchBookByIsbn/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        if (bookService.checkIfExistsByISBN(book.getIsbn())){
            return "failure";
        }
        bookService.addBook(book);
        return "success";
    }
}


