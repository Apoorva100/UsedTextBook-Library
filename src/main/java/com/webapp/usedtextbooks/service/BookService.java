//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.service;
import com.webapp.usedtextbooks.enums.TransactionType;
import com.webapp.usedtextbooks.exception.ResourceNotFoundException;
import com.webapp.usedtextbooks.model.Book;
import com.webapp.usedtextbooks.model.Transaction;
import com.webapp.usedtextbooks.repository.BookRepository;
import com.webapp.usedtextbooks.repository.TransactionRepository;
import com.webapp.usedtextbooks.service.serviceInterface.BookServiceInterface;
import com.webapp.usedtextbooks.util.BookServiceDes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.webapp.usedtextbooks.util.BookServiceDes.bookEntityToBook;
import static com.webapp.usedtextbooks.util.BookServiceDes.bookToBookEntity;

@Slf4j
@Service
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private BigDecimal depreciation = new BigDecimal("0.9");

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public boolean sold_by_id(Long id) {
            try {
                Book toBeSoldBook = findBookById(id);
                if (toBeSoldBook.getIsDeleted() == 1) {
                    return false;
                }
                toBeSoldBook.setTransactionCount(toBeSoldBook.getTransactionCount() + 1);
                toBeSoldBook.setIsDeleted((short) 1);
                bookRepository.save(toBeSoldBook);
                Transaction record = new Transaction();
                record.setBookId(id);
                record.setUserId(1L);
                record.setTransactionType(TransactionType.SELL);
                record.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
                record.setTransactionPrice(toBeSoldBook.getPrice());
                transactionRepository.save(record);
                return true;
            } catch (Exception e) {
                return false;
            }
    }


    @Transactional
    @Override
    public boolean UserSell(Book toBeBoughtBook) {
    try {
        if (toBeBoughtBook != null) {
            // buy one book already existed in the inventory back
            if (getBookByISBN(toBeBoughtBook.getIsbn())!=null) {
                Book existedBook = getBookByISBN(toBeBoughtBook.getIsbn());
                if (existedBook.getTransactionCount() < 10 && existedBook.getIsDeleted() == 1) {
                    double userSellPrice = toBeBoughtBook.getPrice().doubleValue();
                    double inventoryPurchasePrice = existedBook.getPrice().multiply(depreciation).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    if (userSellPrice==inventoryPurchasePrice) {
                        existedBook.setPrice(toBeBoughtBook.getPrice());
                        existedBook.setTransactionCount(existedBook.getTransactionCount() + 1);
                        existedBook.setIsDeleted((short) 0);
                        bookRepository.save(existedBook);
                        Transaction record = new Transaction();
                        record.setBookId(existedBook.getId());
                        record.setUserId(1L);
                        record.setTransactionType(TransactionType.BUY);
                        record.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
                        record.setTransactionPrice(existedBook.getPrice());
                        transactionRepository.save(record);
                        return true;
                    } else {
                        System.out.println("Price doesn't match.");
                        return false;
                    }
                } else {
                    System.out.println("TransactionCount >= 10 or Book is still in the inventory.");
                    return false;
                }
            }
        }
    } catch (Exception e) {
        toBeBoughtBook.setIsDeleted((short) 0);
        toBeBoughtBook.setTransactionCount(0);
        toBeBoughtBook.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        bookRepository.save(toBeBoughtBook);
        Transaction record = new Transaction();
        record.setUserId(1L);
        record.setBookId(toBeBoughtBook.getId());
        record.setTransactionType(TransactionType.BUY);
        record.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        record.setTransactionPrice(toBeBoughtBook.getPrice());
        transactionRepository.save(record);
        System.out.println("New book has been added successfully to the inventory.");
        return true;
    }
        return false;
    }


    @Transactional
    @Override
    public Book addBook(Book book) {
        bookRepository.findByIsbn(book.getIsbn())
                .ifPresent(s -> {
                    throw new ResourceNotFoundException("The book with ISBN " + book.getIsbn() + " already exists.");
                });

        //Set Time
        book.setTransactionCount(0);

        // Set the createTime field to the current timestamp
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        book.setCreateTime(currentTimestamp);

        book.setIsDeleted((short) 0);

        return bookEntityToBook(
                bookRepository.save(bookToBookEntity(book)));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Override
    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookServiceDes::bookEntityToBook)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
    @Override
    public boolean checkIfExistsByISBN(String isbn){
        Optional<Book> foundBook = bookRepository.findByIsbn(isbn);
        return foundBook.isPresent();
    }
}
