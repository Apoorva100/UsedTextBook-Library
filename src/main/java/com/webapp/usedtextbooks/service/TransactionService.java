//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.service;

import com.webapp.usedtextbooks.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

}
