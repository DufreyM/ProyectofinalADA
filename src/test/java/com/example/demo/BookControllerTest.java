package com.example.demo;

import com.example.demo.controllers.BookController;
import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.service.completes.BookServiceComplete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookServiceComplete bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMongoBook_Success() {
        MongoBook mockMongoBook = new MongoBook();
        when(bookService.createBook(mockMongoBook)).thenReturn(mockMongoBook);

        ResponseEntity<MongoBook> response = bookController.createMongoBook(mockMongoBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoBook, response.getBody());
        verify(bookService, times(1)).createBook(mockMongoBook);
    }

    @Test
    public void testCreateJpaBook_Success() {
        BookJpa mockBookJpa = new BookJpa();
        when(bookService.createBook(mockBookJpa)).thenReturn(mockBookJpa);

        ResponseEntity<BookJpa> response = bookController.createJpaBook(mockBookJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBookJpa, response.getBody());
        verify(bookService, times(1)).createBook(mockBookJpa);
    }

    @Test
    public void testGetMongoBookById_Success() {
        MongoBook mockMongoBook = new MongoBook();
        when(bookService.getBookById("1")).thenReturn(mockMongoBook);

        ResponseEntity<MongoBook> response = bookController.getMongoBookById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoBook, response.getBody());
        verify(bookService, times(1)).getBookById("1");
    }

    @Test
    public void testGetJpaBookById_Success() {
        BookJpa mockBookJpa = new BookJpa();
        when(bookService.getBookById(1L)).thenReturn(mockBookJpa);

        ResponseEntity<BookJpa> response = bookController.getJpaBookById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBookJpa, response.getBody());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testGetAllMongoBooks_Success() {
        MongoBook mockMongoBook1 = new MongoBook();
        MongoBook mockMongoBook2 = new MongoBook();
        List<MongoBook> mockMongoBookList = Arrays.asList(mockMongoBook1, mockMongoBook2);

        when(bookService.getAllBooksM()).thenReturn(mockMongoBookList);

        ResponseEntity<List<MongoBook>> response = bookController.getAllMongoBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoBookList, response.getBody());
        verify(bookService, times(1)).getAllBooksM();
    }

    @Test
    public void testGetAllJpaBooks_Success() {
        BookJpa mockBookJpa1 = new BookJpa();
        BookJpa mockBookJpa2 = new BookJpa();
        List<BookJpa> mockBookJpaList = Arrays.asList(mockBookJpa1, mockBookJpa2);

        when(bookService.getAllBooks()).thenReturn(mockBookJpaList);

        ResponseEntity<List<BookJpa>> response = bookController.getAllJpaBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBookJpaList, response.getBody());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testUpdateMongoBook_Success() {
        MongoBook mockMongoBook = new MongoBook();
        when(bookService.updateBook("1", mockMongoBook)).thenReturn(mockMongoBook);

        ResponseEntity<MongoBook> response = bookController.updateMongoBook("1", mockMongoBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoBook, response.getBody());
        verify(bookService, times(1)).updateBook("1", mockMongoBook);
    }

    @Test
    public void testUpdateJpaBook_Success() {
        BookJpa mockBookJpa = new BookJpa();
        when(bookService.updateBook(1L, mockBookJpa)).thenReturn(mockBookJpa);

        ResponseEntity<BookJpa> response = bookController.updateJpaBook(1L, mockBookJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBookJpa, response.getBody());
        verify(bookService, times(1)).updateBook(1L, mockBookJpa);
    }

    @Test
    public void testDeleteMongoBook_Success() {
        ResponseEntity<Void> response = bookController.deleteMongoBook("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).deleteBook("1");
    }

    @Test
    public void testDeleteJpaBook_Success() {
        ResponseEntity<Void> response = bookController.deleteJpaBook(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
