package io.github.akumosstl.springbookapi.repository;

import io.github.akumosstl.springbookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_NameContainingIgnoreCase(String author);
    List<Book> findByGenre_NameContainingIgnoreCase(String genre);

    List<Book> findByAuthor_NameContainingIgnoreCaseAndGenre_NameContainingIgnoreCase(String author, String genre);

    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',:title,'%')")
    List<Book> findBooksWithPartOfTitle(@Param("title") String title);
}
