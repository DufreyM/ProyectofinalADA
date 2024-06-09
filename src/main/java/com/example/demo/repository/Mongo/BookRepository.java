package com.example.demo.repository.Mongo;

import com.example.demo.model.mongo.MongoBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<MongoBook, String> {
}

