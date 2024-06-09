package com.example.demo.controllers;

import com.example.demo.model.jpa.LoanJpa;
import com.example.demo.model.mongo.MongoLoan;
import com.example.demo.service.completes.LoanServiceComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanServiceComplete loanService;

    @PostMapping("/mongo")
    public ResponseEntity<MongoLoan> createMongoLoan(@RequestBody MongoLoan mongoLoan) {
        MongoLoan createdMongoLoan = loanService.createLoan(mongoLoan);
        return ResponseEntity.ok(createdMongoLoan);
    }

    @PostMapping("/jpa")
    public ResponseEntity<LoanJpa> createJpaLoan(@RequestBody LoanJpa loanJpa) {
        LoanJpa createdLoanJpa = loanService.createLoan(loanJpa);
        return ResponseEntity.ok(createdLoanJpa);
    }

    @GetMapping("/mongo/{id}")
    public ResponseEntity<MongoLoan> getMongoLoanById(@PathVariable String id) {
        MongoLoan mongoLoan = loanService.getLoanById(id);
        if (mongoLoan != null) {
            return ResponseEntity.ok(mongoLoan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<LoanJpa> getJpaLoanById(@PathVariable Long id) {
        LoanJpa loanJpa = loanService.getLoanById(id);
        if (loanJpa != null) {
            return ResponseEntity.ok(loanJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mongo")
    public ResponseEntity<List<MongoLoan>> getAllMongoLoans() {
        List<MongoLoan> mongoLoans = loanService.getAllLoansM();
        return ResponseEntity.ok(mongoLoans);
    }

    @GetMapping("/jpa")
    public ResponseEntity<List<LoanJpa>> getAllJpaLoans() {
        List<LoanJpa> jpaLoans = loanService.getAllLoans();
        return ResponseEntity.ok(jpaLoans);
    }

    @PutMapping("/mongo/{id}")
    public ResponseEntity<MongoLoan> updateMongoLoan(@PathVariable String id, @RequestBody MongoLoan mongoLoan) {
        MongoLoan updatedMongoLoan = loanService.updateLoan(id, mongoLoan);
        if (updatedMongoLoan != null) {
            return ResponseEntity.ok(updatedMongoLoan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/jpa/{id}")
    public ResponseEntity<LoanJpa> updateJpaLoan(@PathVariable Long id, @RequestBody LoanJpa loanJpa) {
        LoanJpa updatedLoanJpa = loanService.updateLoan(id, loanJpa);
        if (updatedLoanJpa != null) {
            return ResponseEntity.ok(updatedLoanJpa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/mongo/{id}")
    public ResponseEntity<Void> deleteMongoLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteJpaLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
