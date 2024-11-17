package br.com.magementms.controller;


import br.com.magementms.dto.BookFilter;
import br.com.magementms.dto.BookRequest;
import br.com.magementms.dto.BookResponse;
import br.com.magementms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/new")
    public DeferredResult<ResponseEntity<BookResponse>> newBook(@RequestBody BookRequest bookRequest) {
        DeferredResult<ResponseEntity<BookResponse>> dr = new DeferredResult<>();
        BookResponse bookResponse = bookService.newBook(bookRequest);
        dr.setResult(ResponseEntity.ok(bookResponse));
        return dr;
    }

    @GetMapping("/all")
    public DeferredResult<ResponseEntity<Page<BookResponse>>> getAllBooks(@ModelAttribute() BookFilter bookFilter,
                                                                          @PageableDefault(page = 0, size = 10, sort = "author", direction = Sort.Direction.ASC) Pageable pageable) {
        DeferredResult<ResponseEntity<Page<BookResponse>>> dr = new DeferredResult<>();
        Page<BookResponse> bookResponse = bookService.getAllBooks(pageable,bookFilter);
        dr.setResult(ResponseEntity.ok(bookResponse));
        return dr;
    }

    @GetMapping("/{id}")
    public DeferredResult<ResponseEntity<BookResponse>> getBookById(@PathVariable(value = "id") Long id){
        DeferredResult<ResponseEntity<BookResponse>> dr = new DeferredResult<>();
        BookResponse bookResponse = bookService.getById(id);
        dr.setResult(ResponseEntity.ok(bookResponse));
        return dr;
    }

    @PutMapping("/update")
    public DeferredResult<ResponseEntity<BookResponse>> updateBook(@RequestBody BookRequest bookRequest) {
        DeferredResult<ResponseEntity<BookResponse>> dr = new DeferredResult<>();
        BookResponse bookResponse = bookService.updateBook(bookRequest);
        dr.setResult(ResponseEntity.ok(bookResponse));
        return dr;
    }

    @DeleteMapping("/{id}")
    public DeferredResult<ResponseEntity<BookResponse>> deleteBook(@PathVariable(value = "id") Long id){
        DeferredResult<ResponseEntity<BookResponse>> dr = new DeferredResult<>();
        BookResponse bookResponse = bookService.deleteBook(id);
        dr.setResult(ResponseEntity.ok(bookResponse));
        return dr;
    }
}
