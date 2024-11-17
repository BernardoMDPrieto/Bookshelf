package br.com.magementms.model;


import br.com.magementms.dto.BookRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(length = 50)
    private String publisher;


    //TODO Implementar uma maneira de controlar as linguagens inseridas
    @Column(nullable = false, length = 20)
    private String language;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String category;

    @Column(length = 50)
    private String translator;
}
