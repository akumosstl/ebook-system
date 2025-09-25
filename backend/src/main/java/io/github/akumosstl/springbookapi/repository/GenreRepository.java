package io.github.akumosstl.springbookapi.repository;

import io.github.akumosstl.springbookapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
