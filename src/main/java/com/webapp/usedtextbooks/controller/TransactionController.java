//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.controller;

import com.webapp.usedtextbooks.model.Transaction;
import com.webapp.usedtextbooks.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    public TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}