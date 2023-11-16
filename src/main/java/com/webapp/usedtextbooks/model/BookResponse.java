//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
public class BookResponse {

    @NonNull
    private final Long id;

    @NonNull
    private final String isbn;

    @NonNull
    private final String title;

    @NonNull
    private final String author;

    private final String edition;

    @NonNull
    private final BigDecimal price;

    @NonNull
    private final Timestamp createTime;

    @NonNull
    private final Integer transactionCount;

    @NonNull
    private final Short isDeleted;


}