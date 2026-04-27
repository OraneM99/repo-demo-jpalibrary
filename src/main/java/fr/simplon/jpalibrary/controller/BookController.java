package fr.simplon.jpalibrary.controller;

import fr.simplon.jpalibrary.model.Book;
import fr.simplon.jpalibrary.repository.BookRepository;
import fr.simplon.jpalibrary.service.BookService;
import fr.simplon.jpalibrary.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookRepository.findBookByTitle(title);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.add(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        Book updatedBook = bookService.update(bookId, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId) {
        Book deletedBook = bookService.delete(bookId);
        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }
}
