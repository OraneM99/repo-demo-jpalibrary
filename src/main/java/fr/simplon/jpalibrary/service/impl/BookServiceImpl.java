package fr.simplon.jpalibrary.service.impl;

import fr.simplon.jpalibrary.model.Author;
import fr.simplon.jpalibrary.model.Book;
import fr.simplon.jpalibrary.model.Category;
import fr.simplon.jpalibrary.repository.AuthorRepository;
import fr.simplon.jpalibrary.repository.BookRepository;
import fr.simplon.jpalibrary.repository.CategoryRepository;
import fr.simplon.jpalibrary.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepositoryInjected, AuthorRepository authorRepositoryInjected, CategoryRepository categoryRepositoryInjected) {
        this.bookRepository = bookRepositoryInjected;
        this.authorRepository = authorRepositoryInjected;
        this.categoryRepository = categoryRepositoryInjected;
    }

    // Logique pour vérifier si un auteur existe déjà
    private List<Author> resolveAuthors(List<Author> requestAuthors) {

        List<Author> authors = new ArrayList<>();

        for (Author a : requestAuthors) {
            if (a.getId() != null) {
                Author existing = authorRepository.findById(a.getId()).orElse(null);
                if (existing != null) {
                    authors.add(existing);
                }
            } else {
                authors.add(a);
            }
        }
        return authors;
    }

    // Logique pour vérifier si une catégorie existe déjà
    private Category resolveCategory(Category requestCategory) {
        if (requestCategory == null) {
            return null;
        }
        if (requestCategory.getId() != null) {
            return categoryRepository.findById(requestCategory.getId()).orElse(null);
        }
        return requestCategory;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream()
                .distinct()
                .toList();
    }

    @Override
    public Book add(Book bookRequest) {

       Book newBook = new Book();
       newBook.setTitle(bookRequest.getTitle());
       newBook.setDescription(bookRequest.getDescription());
       newBook.setCategory(resolveCategory(bookRequest.getCategory()));
       newBook.setAuthors(resolveAuthors(bookRequest.getAuthors()));
       newBook.setAvailable(bookRequest.getAvailable());

       return bookRepository.save(newBook);
    }

    @Override
    public Book update(Long id, Book bookRequest) {
        Book bookUpdated = bookRepository.findBookById(id);

        bookUpdated.setTitle(bookRequest.getTitle());
        bookUpdated.setDescription(bookRequest.getDescription());
        bookUpdated.setAvailable(bookRequest.getAvailable());
        bookUpdated.setCategory(resolveCategory(bookRequest.getCategory()));
        bookUpdated.setAuthors(resolveAuthors(bookRequest.getAuthors()));

        return bookRepository.save(bookUpdated);
    }

    @Override
    public Book delete(Long bookId) {
        Book bookToDelete = bookRepository.findBookById(bookId);
        bookRepository.delete(bookToDelete);
        return bookToDelete;
    }
}
