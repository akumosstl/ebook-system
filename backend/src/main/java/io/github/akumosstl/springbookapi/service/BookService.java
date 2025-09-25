package io.github.akumosstl.springbookapi.service;

import io.github.akumosstl.springbookapi.controller.BookDto;
import io.github.akumosstl.springbookapi.model.Author;
import io.github.akumosstl.springbookapi.model.Book;
import io.github.akumosstl.springbookapi.model.Genre;
import io.github.akumosstl.springbookapi.repository.AuthorRepository;
import io.github.akumosstl.springbookapi.repository.BookRepository;
import io.github.akumosstl.springbookapi.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository repo, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = repo;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public Optional<Book> findByIdToUpdate(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<BookDto> findById(Long id) {
        return bookRepository.findById(id).map(this::convert);
    }

    public Book save(Book b) {
        Author author = b.getAuthor();
        if (author != null && author.getName() != null) {
            List<Author> authors = authorRepository.findByName(author.getName());
            if (!authors.isEmpty()) {
                b.setAuthor(authors.get(0));
            } else {
                b.setAuthor(authorRepository.save(author));
            }
        }

        Genre genre = b.getGenre();
        if (genre != null && genre.getName() != null) {
            List<Genre> genres = genreRepository.findByName(genre.getName());
            if (!genres.isEmpty()) {
                b.setGenre(genres.get(0));
            } else {
                b.setGenre(genreRepository.save(genre));
            }
        }

        return bookRepository.save(b);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> search(String title) {
        return bookRepository.findBooksWithPartOfTitle(title).stream().map(this::convert).collect(Collectors.toList());
    }

    private BookDto convert(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor().getName());
        dto.setTitle(book.getTitle());
        dto.setGenre(book.getGenre().getName());
        return dto;
    }
}
