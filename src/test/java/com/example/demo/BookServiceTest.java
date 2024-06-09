package com.example.demo;

import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.repository.JPA.BookJpaRepository;
import com.example.demo.repository.Mongo.BookRepository;
import com.example.demo.service.completes.BookServiceComplete;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookJpaRepository bookJpaRepository;

    @InjectMocks
    private BookServiceComplete bookService;

    @Test
    public void testCreateBook_Mongo() {
        MongoBook mongoBook = new MongoBook();
        mongoBook.setTitle("Test Book");
        when(bookRepository.save(mongoBook)).thenReturn(mongoBook);

        MongoBook createdBook = bookService.createBook(mongoBook);
        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
    }

    @Test
    public void testCreateBook_PostgreSQL() {
        BookJpa book = new BookJpa();
        book.setTitle("Test Book");
        when(bookJpaRepository.save(book)).thenReturn(book);

        BookJpa createdBook = bookService.createBook(book);
        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
    }

    @Test
    public void testGetBookById_Mongo() {
        MongoBook mongoBook = new MongoBook();
        mongoBook.setId("1");
        when(bookRepository.findById("1")).thenReturn(Optional.of(mongoBook));

        MongoBook retrievedBook = bookService.getBookById("1");
        assertNotNull(retrievedBook);
        assertEquals("1", retrievedBook.getId());
    }

    @Test
    public void testGetBookById_PostgreSQL() {
        BookJpa book = new BookJpa();
        book.setId(1L);
        when(bookJpaRepository.findById(1L)).thenReturn(Optional.of(book));

        BookJpa retrievedBook = bookService.getBookById(1L);
        assertNotNull(retrievedBook);
        assertEquals(1L, retrievedBook.getId());
    }
}
