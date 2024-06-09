package com.example.demo.repository.JPA;

import com.example.demo.model.jpa.LoanJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanJpaRepository extends JpaRepository<LoanJpa, Long> {
}