package com.example.demo.repository.Mongo;

import com.example.demo.model.mongo.MongoLoan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends MongoRepository<MongoLoan, String> {
}
