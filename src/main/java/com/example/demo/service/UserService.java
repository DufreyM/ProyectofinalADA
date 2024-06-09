package com.example.demo.service;

import com.example.demo.model.jpa.UserJpa;
import com.example.demo.model.mongo.MongoUser;

import java.util.List;

public interface UserService {
    MongoUser createMongoUser(MongoUser mongoUser);
    UserJpa createUserJpa(UserJpa userJpa);

    MongoUser getMongoUserById(String id);
    UserJpa getUserJpaById(Long id);

    List<MongoUser> getAllMongoUsers();
    List<UserJpa> getAllJpaUsers();

    MongoUser updateMongoUser(String id, MongoUser mongoUser);
    UserJpa updateUserJpa(Long id, UserJpa userJpa);

    void deleteMongoUser(String id);
    void deleteUserJpa(Long id);
}