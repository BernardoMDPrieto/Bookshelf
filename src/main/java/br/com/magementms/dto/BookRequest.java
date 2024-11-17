package br.com.magementms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String language;

    private LocalDate publishedDate;

    private BigDecimal price;

    private String description;

    private String category;

    private Long quantity;

    private String translator;
}
