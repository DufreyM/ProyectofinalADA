package com.example.demo;

import com.example.demo.controllers.LoanController;
import com.example.demo.model.jpa.LoanJpa;
import com.example.demo.model.mongo.MongoLoan;
import com.example.demo.service.completes.LoanServiceComplete;
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

public class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanServiceComplete loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMongoLoan_Success() {
        MongoLoan mockMongoLoan = new MongoLoan();
        when(loanService.createLoan(mockMongoLoan)).thenReturn(mockMongoLoan);

        ResponseEntity<MongoLoan> response = loanController.createMongoLoan(mockMongoLoan);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoLoan, response.getBody());
        verify(loanService, times(1)).createLoan(mockMongoLoan);
    }

    @Test
    public void testCreateJpaLoan_Success() {
        LoanJpa mockLoanJpa = new LoanJpa();
        when(loanService.createLoan(mockLoanJpa)).thenReturn(mockLoanJpa);

        ResponseEntity<LoanJpa> response = loanController.createJpaLoan(mockLoanJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoanJpa, response.getBody());
        verify(loanService, times(1)).createLoan(mockLoanJpa);
    }

    @Test
    public void testGetMongoLoanById_Success() {
        MongoLoan mockMongoLoan = new MongoLoan();
        when(loanService.getLoanById("1")).thenReturn(mockMongoLoan);

        ResponseEntity<MongoLoan> response = loanController.getMongoLoanById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoLoan, response.getBody());
        verify(loanService, times(1)).getLoanById("1");
    }

    @Test
    public void testGetJpaLoanById_Success() {
        LoanJpa mockLoanJpa = new LoanJpa();
        when(loanService.getLoanById(1L)).thenReturn(mockLoanJpa);

        ResponseEntity<LoanJpa> response = loanController.getJpaLoanById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoanJpa, response.getBody());
        verify(loanService, times(1)).getLoanById(1L);
    }

    @Test
    public void testGetAllMongoLoans_Success() {
        MongoLoan mockMongoLoan1 = new MongoLoan();
        MongoLoan mockMongoLoan2 = new MongoLoan();
        List<MongoLoan> mockMongoLoanList = Arrays.asList(mockMongoLoan1, mockMongoLoan2);

        when(loanService.getAllLoansM()).thenReturn(mockMongoLoanList);

        ResponseEntity<List<MongoLoan>> response = loanController.getAllMongoLoans();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoLoanList, response.getBody());
        verify(loanService, times(1)).getAllLoansM();
    }

    @Test
    public void testGetAllJpaLoans_Success() {
        LoanJpa mockLoanJpa1 = new LoanJpa();
        LoanJpa mockLoanJpa2 = new LoanJpa();
        List<LoanJpa> mockLoanJpaList = Arrays.asList(mockLoanJpa1, mockLoanJpa2);

        when(loanService.getAllLoans()).thenReturn(mockLoanJpaList);

        ResponseEntity<List<LoanJpa>> response = loanController.getAllJpaLoans();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoanJpaList, response.getBody());
        verify(loanService, times(1)).getAllLoans();
    }

    @Test
    public void testUpdateMongoLoan_Success() {
        MongoLoan mockMongoLoan = new MongoLoan();
        when(loanService.updateLoan("1", mockMongoLoan)).thenReturn(mockMongoLoan);

        ResponseEntity<MongoLoan> response = loanController.updateMongoLoan("1", mockMongoLoan);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoLoan, response.getBody());
        verify(loanService, times(1)).updateLoan("1", mockMongoLoan);
    }

    @Test
    public void testUpdateJpaLoan_Success() {
        LoanJpa mockLoanJpa = new LoanJpa();
        when(loanService.updateLoan(1L, mockLoanJpa)).thenReturn(mockLoanJpa);

        ResponseEntity<LoanJpa> response = loanController.updateJpaLoan(1L, mockLoanJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockLoanJpa, response.getBody());
        verify(loanService, times(1)).updateLoan(1L, mockLoanJpa);
    }

    @Test
    public void testDeleteMongoLoan_Success() {
        ResponseEntity<Void> response = loanController.deleteMongoLoan("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(loanService, times(1)).deleteLoan("1");
    }

    @Test
    public void testDeleteJpaLoan_Success() {
        ResponseEntity<Void> response = loanController.deleteJpaLoan(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(loanService, times(1)).deleteLoan(1L);
    }
}
