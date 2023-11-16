//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu
package com.webapp.usedtextbooks.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "books" )
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ISBN", nullable = false, length = 20)
    private String isbn;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "author", nullable = false, length = 255)
    private String author;

    @Column(name = "edition", length = 50)
    private String edition;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @Column(name = "transaction_count", columnDefinition = "INT DEFAULT 0")
    private Integer transactionCount;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    @Check(constraints = "is_deleted IN (0, 1)")
    private Short isDeleted;

}
