package com.example.demo.controllers;

import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.service.completes.BookServiceComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceComplete bookService;

    @PostMapping("/mongo")
    public ResponseEntity<MongoBook> createMongoBook(@RequestBody MongoBook mongoBook) {
        MongoBook createdMongoBook = bookService.createBook(mongoBook);
        return ResponseEntity.ok(createdMongoBook);
    }

    @PostMapping("/jpa")
    public ResponseEntity<BookJpa> createJpaBook(@RequestBody BookJpa bookJpa) {
        BookJpa createdBookJpa = bookService.createBook(bookJpa);
        return ResponseEntity.ok(createdBookJpa);
    }

    @GetMapping("/mongo/{id}")
    public ResponseEntity<MongoBook> getMongoBookById(@PathVariable String id) {
        MongoBook mongoBook = bookService.getBookById(id);
        if (mongoBook != null) {
            return ResponseEntity.ok(mongoBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<BookJpa> getJpaBookById(@PathVariable Long id) {
        BookJpa bookJpa = bookService.getBookById(id);
        if (bookJpa != null) {
            return ResponseEntity.ok(bookJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mongo")
    public ResponseEntity<List<MongoBook>> getAllMongoBooks() {
        List<MongoBook> mongoBooks = bookService.getAllBooksM();
        return ResponseEntity.ok(mongoBooks);
    }

    @GetMapping("/jpa")
    public ResponseEntity<List<BookJpa>> getAllJpaBooks() {
        List<BookJpa> jpaBooks = bookService.getAllBooks();
        return ResponseEntity.ok(jpaBooks);
    }

    @PutMapping("/mongo/{id}")
    public ResponseEntity<MongoBook> updateMongoBook(@PathVariable String id, @RequestBody MongoBook mongoBook) {
        MongoBook updatedMongoBook = bookService.updateBook(id, mongoBook);
        if (updatedMongoBook != null) {
            return ResponseEntity.ok(updatedMongoBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/jpa/{id}")
    public ResponseEntity<BookJpa> updateJpaBook(@PathVariable Long id, @RequestBody BookJpa bookJpa) {
        BookJpa updatedBookJpa = bookService.updateBook(id, bookJpa);
        if (updatedBookJpa != null) {
            return ResponseEntity.ok(updatedBookJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mongo/{id}")
    public ResponseEntity<Void> deleteMongoBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteJpaBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
