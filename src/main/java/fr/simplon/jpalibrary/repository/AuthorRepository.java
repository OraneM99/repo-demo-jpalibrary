package fr.simplon.jpalibrary.repository;

import fr.simplon.jpalibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
