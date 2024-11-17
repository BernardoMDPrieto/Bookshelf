package br.com.magementms.service;


import br.com.magementms.dto.BookFilter;
import br.com.magementms.dto.BookRequest;
import br.com.magementms.dto.BookResponse;
import br.com.magementms.filter.BooksSpecification;
import br.com.magementms.model.Book;
import br.com.magementms.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse newBook(BookRequest bookRequest) {
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthorAndPublisherAndPublishedDateAndTranslator(
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getPublisher(),
                bookRequest.getPublishedDate(),
                bookRequest.getTranslator());

        if (existingBook.isPresent()) {
            throw new IllegalArgumentException("O livro já existe com o mesmo título, autor, editora e ano de publicação.");
        }

        Book newBook = createBook(bookRequest);

        Book savedBook = bookRepository.save(newBook);

        return new BookResponse(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getAuthor(),
                savedBook.getPublisher(),
                savedBook.getLanguage(),
                savedBook.getPublishedDate(),
                savedBook.getPrice(),
                savedBook.getDescription(),
                savedBook.getCategory(),
                savedBook.getQuantity(),
                savedBook.getTranslator()
        );

    }

    private static Book createBook(BookRequest bookRequest) {
        Book newBook = new Book();
        newBook.setTitle(bookRequest.getTitle());
        newBook.setAuthor(bookRequest.getAuthor());
        newBook.setPublisher(bookRequest.getPublisher());
        newBook.setLanguage(bookRequest.getLanguage());
        newBook.setPublishedDate(bookRequest.getPublishedDate());
        newBook.setPrice(bookRequest.getPrice());
        newBook.setDescription(bookRequest.getDescription());
        newBook.setCategory(bookRequest.getCategory());
        newBook.setQuantity(bookRequest.getQuantity());
        newBook.setTranslator(bookRequest.getTranslator());
        return newBook;
    }

    private BookResponse converToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        BeanUtils.copyProperties(book, bookResponse);
        return bookResponse;
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getAllBooks(Pageable pageable, BookFilter bookFilter) {
        Specification<Book> spec = BooksSpecification.filterBy(bookFilter);

        Page<Book> books = bookRepository.findAll(spec,pageable);

        List<BookResponse> bookResponses = books.getContent().stream().map(this::converToBookResponse).toList();
        return new PageImpl<>(bookResponses, pageable, books.getTotalElements());
    }

    @Transactional(readOnly = true)
    public BookResponse getById(Long id) {
        return bookRepository.findById(id).map(this::converToBookResponse).orElse(null);
    }

    public BookResponse updateBook(BookRequest bookRequest) {
        Book ExistingBook = bookRepository.findById(bookRequest.getId()).orElseThrow(RuntimeException::new);
        BeanUtils.copyProperties(bookRequest, ExistingBook);
        bookRepository.save(ExistingBook);
        return converToBookResponse(ExistingBook);
    }

    public BookResponse deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(existingBook);
        return converToBookResponse(existingBook);
    }
}
