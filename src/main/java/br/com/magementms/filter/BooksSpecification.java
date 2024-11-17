package br.com.magementms.filter;

import br.com.magementms.dto.BookFilter;
import br.com.magementms.model.Book;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BooksSpecification {
    public static Specification<Book> byTitle(String title) {
        return (root, query, cb) -> title != null ? cb.like(root.get("title"), "%" + title + "%") : null;
    }

    public static Specification<Book> byAuthor(String author) {
        return (root, query, cb) -> author != null ? cb.like(root.get("author"), "%" + author + "%") : null;
    }

    public static Specification<Book> byPublisher(String publisher) {
        return (root, query, cb) -> publisher != null ? cb.like(root.get("publisher"), "%" + publisher + "%") : null;
    }

    public static Specification<Book> byLanguage(String language) {
        return (root, query, cb) -> language != null ? cb.equal(root.get("language"), language) : null;
    }

    public static Specification<Book> byPublishDate(LocalDate publishedDate) {
        return (root, query, cb) -> publishedDate != null ? cb.lessThan(root.get("publishedDate"), publishedDate) : null;
    }

    public static Specification<Book> byPrice(BigDecimal price) {
        return (root, query, cb) -> price != null ? cb.equal(root.get("price"), price) : null;
    }

    public static Specification<Book> byDescription(String description) {
        return (root, query, cb) -> description != null ? cb.like(root.get("description"), "%" + description + "%") : null;
    }

    public static Specification<Book> byCategory(String category) {
        return (root, query, cb) -> category != null ? cb.equal(root.get("category"), category) : null;
    }

    public static Specification<Book> byQuantity(Long quantity) {
        return (root, query, cb) -> quantity != null ? cb.equal(root.get("quantity"), quantity) : null;
    }

    public static Specification<Book> byTranslator(String translator) {
        return (root, query, cb) -> translator != null ? cb.like(root.get("translator"), "%" + translator + "%") : null;
    }

    public static Specification<Book> filterBy(BookFilter filter) {
        Specification<Book> specification = Specification.where(null);

        if (filter.getTitle() != null) {
            specification = specification.and(byTitle(filter.getTitle()));
        }
        if (filter.getAuthor() != null) {
            specification = specification.and(byAuthor(filter.getAuthor()));
        }
        if (filter.getPublisher() != null) {
            specification = specification.and(byPublisher(filter.getPublisher()));
        }
        if (filter.getLanguage() != null) {
            specification = specification.and(byLanguage(filter.getLanguage()));
        }
        if (filter.getPublishedDate() != null) {
            specification = specification.and(byPublishDate(filter.getPublishedDate()));
        }
        if (filter.getPrice() != null) {
            specification = specification.and(byPrice(filter.getPrice()));
        }
        if (filter.getDescription() != null) {
            specification = specification.and(byDescription(filter.getDescription()));
        }
        if (filter.getCategory() != null) {
            specification = specification.and(byCategory(filter.getCategory()));
        }
        if (filter.getQuantity() != null) {
            specification = specification.and(byQuantity(filter.getQuantity()));
        }
        if (filter.getTranslator() != null) {
            specification = specification.and(byTranslator(filter.getTranslator()));
        }

        return specification;
    }
}
