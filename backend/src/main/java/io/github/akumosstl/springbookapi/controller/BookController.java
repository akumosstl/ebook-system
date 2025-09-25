package io.github.akumosstl.springbookapi.controller;

import io.github.akumosstl.springbookapi.model.Author;
import io.github.akumosstl.springbookapi.model.Book;
import io.github.akumosstl.springbookapi.model.Genre;
import io.github.akumosstl.springbookapi.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> all() {
        logger.info("getting all books!");

        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book create(@RequestBody BookDto b) {
        logger.info("creating a new book!");

        Book book = new Book();
        book.setTitle(b.getTitle());
        Author author = new Author();
        author.setName(b.getAuthor());

        Genre genre = new Genre();
        genre.setName(b.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);

        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.findByIdToUpdate(id).map(existing -> {
            existing.setTitle(bookDto.getTitle());
            existing.getAuthor().setName(bookDto.getAuthor());
            existing.getGenre().setName(bookDto.getGenre());

            bookService.save(existing);

            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<BookDto> search(@RequestParam(required = false) String title) {
        return bookService.search(title);
    }
}
