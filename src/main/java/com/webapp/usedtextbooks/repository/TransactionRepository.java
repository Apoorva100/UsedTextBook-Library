//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.repository;

import com.webapp.usedtextbooks.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
