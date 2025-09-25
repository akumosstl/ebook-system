package springbookapi;

import io.github.akumosstl.springbookapi.controller.BookDto;
import io.github.akumosstl.springbookapi.model.Author;
import io.github.akumosstl.springbookapi.model.Book;
import io.github.akumosstl.springbookapi.model.Genre;
import io.github.akumosstl.springbookapi.repository.AuthorRepository;
import io.github.akumosstl.springbookapi.repository.BookRepository;
import io.github.akumosstl.springbookapi.repository.GenreRepository;
import io.github.akumosstl.springbookapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepo;
    private AuthorRepository authorRepo;
    private GenreRepository genreRepo;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepo = mock(BookRepository.class);
        authorRepo = mock(AuthorRepository.class);
        genreRepo = mock(GenreRepository.class);
        bookService = new BookService(bookRepo, authorRepo, genreRepo);
    }

    @Test
    void testSaveBookWithExistingAuthorAndGenre() {
        Author author = new Author();
        author.setName("Autor");
        Genre genre = new Genre();
        genre.setName("Gênero");
        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);

        when(authorRepo.findByName("Autor")).thenReturn(Collections.singletonList(author));
        when(genreRepo.findByName("Gênero")).thenReturn(Collections.singletonList(genre));
        when(bookRepo.save(any(Book.class))).thenReturn(book);

        Book saved = bookService.save(book);

        assertEquals(author, saved.getAuthor());
        assertEquals(genre, saved.getGenre());
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    void testSaveBookWithNewAuthorAndGenre() {
        Author author = new Author();
        author.setName("Novo Autor");
        Genre genre = new Genre();
        genre.setName("Novo Gênero");
        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);

        when(authorRepo.findByName("Novo Autor")).thenReturn(Collections.emptyList());
        when(authorRepo.save(author)).thenReturn(author);
        when(genreRepo.findByName("Novo Gênero")).thenReturn(Collections.emptyList());
        when(genreRepo.save(genre)).thenReturn(genre);
        when(bookRepo.save(any(Book.class))).thenReturn(book);

        Book saved = bookService.save(book);

        assertEquals(author, saved.getAuthor());
        assertEquals(genre, saved.getGenre());
        verify(authorRepo, times(1)).save(author);
        verify(genreRepo, times(1)).save(genre);
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    void testFindBookById() {
        Author author = new Author();
        author.setName("Autor");
        Genre genre = new Genre();
        genre.setName("Gênero");
        Book book = new Book();
        book.setId(1L);
        book.setAuthor(author);
        book.setGenre(genre);

        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

        Optional<BookDto> found = bookService.findById(1L);

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }
}