package fr.simplon.jpalibrary.service;

import fr.simplon.jpalibrary.model.Book;
import fr.simplon.jpalibrary.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book add(Book book) {
        return this.bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        Book bookUpdated = bookRepository.findBookById(id);
        bookUpdated.setTitle(book.getTitle());
        bookUpdated.setDescription(book.getDescription());
        bookUpdated.setAvailable(book.getAvailable());

        return this.bookRepository.save(bookUpdated);
    }

    public Book delete(Long bookId) {
        Book bookToDelete = bookRepository.findBookById(bookId);
        bookRepository.delete(bookToDelete);
        return bookToDelete;
    }
}
