//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.service.serviceInterface;
import com.webapp.usedtextbooks.model.Book;


public interface BookServiceInterface {

    boolean sold_by_id(Long id);

    boolean UserSell(Book toBeBoughtBook);

    Book addBook(Book book);

    Book findBookById(Long id);

    Book getBookByISBN(String isbn);

    boolean checkIfExistsByISBN(String isbn);
}