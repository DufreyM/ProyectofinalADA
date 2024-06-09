package com.example.demo.service.completes;

import com.example.demo.model.jpa.UserJpa;
import com.example.demo.model.mongo.MongoUser;
import com.example.demo.repository.JPA.UserJpaRepository;
import com.example.demo.repository.Mongo.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceComplete implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public MongoUser createMongoUser(MongoUser mongoUser) {
        return userRepository.save(mongoUser);
    }

    @Override
    public UserJpa createUserJpa(UserJpa userJpa) {
        return userJpaRepository.save(userJpa);
    }

    @Override
    public MongoUser getMongoUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserJpa getUserJpaById(Long id) {
        return userJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<MongoUser> getAllMongoUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserJpa> getAllJpaUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public MongoUser updateMongoUser(String id, MongoUser mongoUser) {
        if (userRepository.existsById(id)) {
            mongoUser.setId(id);
            return userRepository.save(mongoUser);
        }
        return null;
    }

    @Override
    public UserJpa updateUserJpa(Long id, UserJpa userJpa) {
        if (userJpaRepository.existsById(id)) {
            userJpa.setId(id);
            return userJpaRepository.save(userJpa);
        }
        return null;
    }

    @Override
    public void deleteMongoUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserJpa(Long id) {
        userJpaRepository.deleteById(id);
    }
}
