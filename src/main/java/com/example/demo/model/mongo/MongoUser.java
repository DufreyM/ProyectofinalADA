package com.example.demo.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MongoUser {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public MongoUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
