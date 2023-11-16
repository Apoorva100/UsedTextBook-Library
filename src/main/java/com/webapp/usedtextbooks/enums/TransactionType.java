//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    SELL(0), //sell from inventory
    BUY(1); //buy from user

    private final int value;

    TransactionType(int value) {
        this.value = value;
    }

}
