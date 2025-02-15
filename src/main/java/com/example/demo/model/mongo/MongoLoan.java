package com.example.demo.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MongoLoan {
    @Id
    private String id;
    private String userId;
    private String bookId;
    private Date loanDate;
    private Date returnDate;
}
