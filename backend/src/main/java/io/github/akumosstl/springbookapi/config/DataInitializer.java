package io.github.akumosstl.springbookapi.config;

import io.github.akumosstl.springbookapi.model.Author;
import io.github.akumosstl.springbookapi.model.Book;
import io.github.akumosstl.springbookapi.model.Genre;
import io.github.akumosstl.springbookapi.repository.AuthorRepository;
import io.github.akumosstl.springbookapi.repository.BookRepository;
import io.github.akumosstl.springbookapi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Bean
    CommandLineRunner init(BookRepository repo) {
        return args -> {
            sample("Clean Code", "Robert C. Martin", "Software");
            sample("Design Patterns", "Gamma et al.", "Software");
            sample("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        };
    }

    public Book save(Book b) {
        if (b.getAuthor() != null && b.getAuthor().getId() == null) {
            b.setAuthor(authorRepository.save(b.getAuthor()));
        }
        if (b.getGenre() != null && b.getGenre().getId() == null) {
            b.setGenre(genreRepository.save(b.getGenre()));
        }
        return bookRepository.save(b);
    }

    private void sample(String title, String authorName, String genreName) {
        Book b = new Book();

        Author author = new Author();
        author.setName(authorName);

        Genre genre = new Genre();
        genre.setName(genreName);

        b.setTitle(title);
        b.setAuthor(author);
        b.setGenre(genre);

        save(b);
    }
}
