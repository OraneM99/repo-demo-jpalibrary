package fr.simplon.jpalibrary.service;

import fr.simplon.jpalibrary.model.Book;

import java.util.List;

public interface BookService {

    Book add(Book book);

    Book update(Long id, Book book);

    Book delete(Long bookId);
}
