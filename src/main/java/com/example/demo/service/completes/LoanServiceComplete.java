package com.example.demo.service.completes;

import com.example.demo.model.jpa.BookJpa;
import com.example.demo.model.jpa.LoanJpa;
import com.example.demo.model.mongo.MongoBook;
import com.example.demo.model.mongo.MongoLoan;
import com.example.demo.repository.JPA.BookJpaRepository;
import com.example.demo.repository.JPA.LoanJpaRepository;
import com.example.demo.repository.Mongo.BookRepository;
import com.example.demo.repository.Mongo.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceComplete {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanJpaRepository loanJpaRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    public MongoLoan createLoan(MongoLoan mongoLoan) {
        MongoBook mongoBook = bookRepository.findById(mongoLoan.getBookId()).orElse(null);
        if (mongoBook != null && mongoBook.isAvailable()) {
            mongoBook.setAvailable(false);
            bookRepository.save(mongoBook);
            return loanRepository.save(mongoLoan);
        }
        return null;
    }

    public LoanJpa createLoan(LoanJpa loan) {
        BookJpa book = bookJpaRepository.findById(loan.getBookId()).orElse(null);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            bookJpaRepository.save(book);
            return loanJpaRepository.save(loan);
        }
        return null;
    }

    public MongoLoan getLoanById(String id) {
        return loanRepository.findById(id).orElse(null);
    }

    public LoanJpa getLoanById(Long id) {
        return loanJpaRepository.findById(id).orElse(null);
    }

    public List<MongoLoan> getAllLoansM() {
        return loanRepository.findAll();
    }

    public List<LoanJpa> getAllLoans() {
        return loanJpaRepository.findAll();
    }

    public MongoLoan updateLoan(String id, MongoLoan mongoLoan) {
        if (loanRepository.existsById(id)) {
            mongoLoan.setId(id);
            return loanRepository.save(mongoLoan);
        }
        return null;
    }

    public LoanJpa updateLoan(Long id, LoanJpa loan) {
        if (loanJpaRepository.existsById(id)) {
            loan.setId(id);
            return loanJpaRepository.save(loan);
        }
        return null;
    }

    public void deleteLoan(String id) {
        MongoLoan mongoLoan = loanRepository.findById(id).orElse(null);
        if (mongoLoan != null) {
            MongoBook mongoBook = bookRepository.findById(mongoLoan.getBookId()).orElse(null);
            if (mongoBook != null) {
                mongoBook.setAvailable(true);
                bookRepository.save(mongoBook);
            }
            loanRepository.deleteById(id);
        }
    }

    public void deleteLoan(Long id) {
        LoanJpa loan = loanJpaRepository.findById(id).orElse(null);
        if (loan != null) {
            BookJpa book = bookJpaRepository.findById(loan.getBookId()).orElse(null);
            if (book != null) {
                book.setAvailable(true);
                bookJpaRepository.save(book);
            }
            loanJpaRepository.deleteById(id);
        }
    }
}