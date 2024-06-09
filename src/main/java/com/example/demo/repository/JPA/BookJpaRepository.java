package com.example.demo.repository.JPA;

import com.example.demo.model.jpa.BookJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookJpa, Long> {
}
