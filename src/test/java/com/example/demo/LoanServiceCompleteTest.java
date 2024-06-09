package com.example.demo;

import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.jpa.LoanJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.model.mongo.MongoLoan;
import com.example.demo.repository.JPA.BookJpaRepository;
import com.example.demo.repository.JPA.LoanJpaRepository;
import com.example.demo.repository.Mongo.BookRepository;
import com.example.demo.repository.Mongo.LoanRepository;
import com.example.demo.service.completes.LoanServiceComplete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanServiceCompleteTest {

    @InjectMocks
    private LoanServiceComplete loanServiceComplete;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanJpaRepository loanJpaRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookJpaRepository bookJpaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateLoan_Mongo_Success() {
        MongoBook mockMongoBook = new MongoBook();
        mockMongoBook.setId("bookId");
        mockMongoBook.setAvailable(true);

        MongoLoan mockMongoLoan = new MongoLoan();
        mockMongoLoan.setBookId("bookId");

        when(bookRepository.findById("bookId")).thenReturn(Optional.of(mockMongoBook));
        when(loanRepository.save(mockMongoLoan)).thenReturn(mockMongoLoan);

        MongoLoan result = loanServiceComplete.createLoan(mockMongoLoan);

        assertNotNull(result);
        verify(bookRepository, times(1)).save(mockMongoBook);
        verify(loanRepository, times(1)).save(mockMongoLoan);
    }

    @Test
    public void testCreateLoan_JPA_Success() {
        BookJpa mockBookJpa = new BookJpa();
        mockBookJpa.setId(1L);
        mockBookJpa.setAvailable(true);

        LoanJpa mockLoanJpa = new LoanJpa();
        mockLoanJpa.setBookId(1L);

        when(bookJpaRepository.findById(1L)).thenReturn(Optional.of(mockBookJpa));
        when(loanJpaRepository.save(mockLoanJpa)).thenReturn(mockLoanJpa);

        LoanJpa result = loanServiceComplete.createLoan(mockLoanJpa);

        assertNotNull(result);
        verify(bookJpaRepository, times(1)).save(mockBookJpa);
        verify(loanJpaRepository, times(1)).save(mockLoanJpa);
    }

    @Test
    public void testGetLoanById_Mongo_Success() {
        MongoLoan mockMongoLoan = new MongoLoan();
        mockMongoLoan.setId("loanId");

        when(loanRepository.findById("loanId")).thenReturn(Optional.of(mockMongoLoan));

        MongoLoan result = loanServiceComplete.getLoanById("loanId");

        assertNotNull(result);
        assertEquals("loanId", result.getId());
        verify(loanRepository, times(1)).findById("loanId");
    }

    @Test
    public void testGetLoanById_JPA_Success() {
        LoanJpa mockLoanJpa = new LoanJpa();
        mockLoanJpa.setId(1L);

        when(loanJpaRepository.findById(1L)).thenReturn(Optional.of(mockLoanJpa));

        LoanJpa result = loanServiceComplete.getLoanById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(loanJpaRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteLoan_Mongo_Success() {
        MongoLoan mockMongoLoan = new MongoLoan();
        mockMongoLoan.setId("loanId");
        mockMongoLoan.setBookId("bookId");

        MongoBook mockMongoBook = new MongoBook();
        mockMongoBook.setId("bookId");

        when(loanRepository.findById("loanId")).thenReturn(Optional.of(mockMongoLoan));
        when(bookRepository.findById("bookId")).thenReturn(Optional.of(mockMongoBook));

        loanServiceComplete.deleteLoan("loanId");

        verify(bookRepository, times(1)).save(mockMongoBook);
        verify(loanRepository, times(1)).deleteById("loanId");
    }

    @Test
    public void testDeleteLoan_JPA_Success() {
        LoanJpa mockLoanJpa = new LoanJpa();
        mockLoanJpa.setId(1L);
        mockLoanJpa.setBookId(1L);

        BookJpa mockBookJpa = new BookJpa();
        mockBookJpa.setId(1L);

        when(loanJpaRepository.findById(1L)).thenReturn(Optional.of(mockLoanJpa));
        when(bookJpaRepository.findById(1L)).thenReturn(Optional.of(mockBookJpa));

        loanServiceComplete.deleteLoan(1L);

        verify(bookJpaRepository, times(1)).save(mockBookJpa);
        verify(loanJpaRepository, times(1)).deleteById(1L);
    }
}
