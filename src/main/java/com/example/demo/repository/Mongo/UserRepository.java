package com.example.demo.repository.Mongo;

import com.example.demo.model.mongo.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<MongoUser, String> {
    Optional<MongoUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
