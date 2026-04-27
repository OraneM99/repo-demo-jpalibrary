package fr.simplon.jpalibrary.repository;

import fr.simplon.jpalibrary.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
