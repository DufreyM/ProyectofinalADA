package com.example.demo.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MongoBook {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;
}
