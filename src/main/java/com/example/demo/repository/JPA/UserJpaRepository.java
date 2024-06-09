package com.example.demo.repository.JPA;

import com.example.demo.model.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByEmail(String email);
    Boolean existsByEmail(String email);
}