//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.util;

import com.webapp.usedtextbooks.model.Book;
import com.webapp.usedtextbooks.model.BookResponse;

public class BookServiceDes {

    public BookServiceDes() {
    }

    public static Book bookToBookEntity(final Book book) {
        return Book.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .edition(book.getEdition())
                .price(book.getPrice())
                .createTime(book.getCreateTime())
                .transactionCount(book.getTransactionCount())
                .isDeleted(book.getIsDeleted())
                .build();
    }

    public static Book bookEntityToBook(final Book bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .edition(bookEntity.getEdition())
                .price(bookEntity.getPrice())
                .createTime(bookEntity.getCreateTime())
                .transactionCount(bookEntity.getTransactionCount())
                .isDeleted(bookEntity.getIsDeleted())
                .build();
    }
    public static BookResponse bookToBookResponse(final Book book){
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .edition(book.getEdition())
                .price(book.getPrice())
                .createTime(book.getCreateTime())
                .transactionCount(book.getTransactionCount())
                .isDeleted(book.getIsDeleted())
                .build();
    }
}