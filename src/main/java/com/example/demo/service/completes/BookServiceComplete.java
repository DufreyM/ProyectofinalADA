package com.example.demo.service.completes;

import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.repository.JPA.BookJpaRepository;
import com.example.demo.repository.Mongo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceComplete {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    public MongoBook createBook(MongoBook mongoBook) {
        return bookRepository.save(mongoBook);
    }

    public BookJpa createBook(BookJpa book) {
        return bookJpaRepository.save(book);
    }

    public MongoBook getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public BookJpa getBookById(Long id) {
        return bookJpaRepository.findById(id).orElse(null);
    }

    public List<MongoBook> getAllBooksM() {
        return bookRepository.findAll();
    }

    public List<BookJpa> getAllBooks() {
        return bookJpaRepository.findAll();
    }

    public MongoBook updateBook(String id, MongoBook mongoBook) {
        if (bookRepository.existsById(id)) {
            mongoBook.setId(id);
            return bookRepository.save(mongoBook);
        }
        return null;
    }

    public BookJpa updateBook(Long id, BookJpa book) {
        if (bookJpaRepository.existsById(id)) {
            book.setId(id);
            return bookJpaRepository.save(book);
        }
        return null;
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public void deleteBook(Long id) {
        bookJpaRepository.deleteById(id);
    }
}
