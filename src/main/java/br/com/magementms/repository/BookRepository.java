package br.com.magementms.repository;

import br.com.magementms.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorAndPublisherAndPublishedDateAndTranslator(
            String title, String author, String publisher, LocalDate publishedDate, String translator);

    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
}
