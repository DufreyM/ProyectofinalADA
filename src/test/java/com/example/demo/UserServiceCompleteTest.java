package com.example.demo;

import com.example.demo.model.jpa.UserJpa;
import com.example.demo.model.mongo.MongoUser;
import com.example.demo.repository.JPA.UserJpaRepository;
import com.example.demo.repository.Mongo.UserRepository;
import com.example.demo.service.completes.UserServiceComplete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceCompleteTest {

    @InjectMocks
    private UserServiceComplete userServiceComplete;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMongoUser_Success() {
        MongoUser mockMongoUser = new MongoUser();
        mockMongoUser.setId("userId");

        when(userRepository.save(mockMongoUser)).thenReturn(mockMongoUser);

        MongoUser result = userServiceComplete.createMongoUser(mockMongoUser);

        assertNotNull(result);
        verify(userRepository, times(1)).save(mockMongoUser);
    }

    @Test
    public void testCreateUserJpa_Success() {
        UserJpa mockUserJpa = new UserJpa();
        mockUserJpa.setId(1L);

        when(userJpaRepository.save(mockUserJpa)).thenReturn(mockUserJpa);

        UserJpa result = userServiceComplete.createUserJpa(mockUserJpa);

        assertNotNull(result);
        verify(userJpaRepository, times(1)).save(mockUserJpa);
    }

    @Test
    public void testGetMongoUserById_Success() {
        MongoUser mockMongoUser = new MongoUser();
        mockMongoUser.setId("userId");

        when(userRepository.findById("userId")).thenReturn(Optional.of(mockMongoUser));

        MongoUser result = userServiceComplete.getMongoUserById("userId");

        assertNotNull(result);
        assertEquals("userId", result.getId());
        verify(userRepository, times(1)).findById("userId");
    }

    @Test
    public void testGetUserJpaById_Success() {
        UserJpa mockUserJpa = new UserJpa();
        mockUserJpa.setId(1L);

        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(mockUserJpa));

        UserJpa result = userServiceComplete.getUserJpaById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userJpaRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllMongoUsers_Success() {
        MongoUser mockMongoUser1 = new MongoUser();
        MongoUser mockMongoUser2 = new MongoUser();
        List<MongoUser> mockMongoUserList = Arrays.asList(mockMongoUser1, mockMongoUser2);

        when(userRepository.findAll()).thenReturn(mockMongoUserList);

        List<MongoUser> result = userServiceComplete.getAllMongoUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllJpaUsers_Success() {
        UserJpa mockUserJpa1 = new UserJpa();
        UserJpa mockUserJpa2 = new UserJpa();
        List<UserJpa> mockUserJpaList = Arrays.asList(mockUserJpa1, mockUserJpa2);

        when(userJpaRepository.findAll()).thenReturn(mockUserJpaList);

        List<UserJpa> result = userServiceComplete.getAllJpaUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userJpaRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateMongoUser_Success() {
        MongoUser mockMongoUser = new MongoUser();
        mockMongoUser.setId("userId");

        when(userRepository.existsById("userId")).thenReturn(true);
        when(userRepository.save(mockMongoUser)).thenReturn(mockMongoUser);

        MongoUser result = userServiceComplete.updateMongoUser("userId", mockMongoUser);

        assertNotNull(result);
        assertEquals("userId", result.getId());
        verify(userRepository, times(1)).save(mockMongoUser);
    }

    @Test
    public void testUpdateUserJpa_Success() {
        UserJpa mockUserJpa = new UserJpa();
        mockUserJpa.setId(1L);

        when(userJpaRepository.existsById(1L)).thenReturn(true);
        when(userJpaRepository.save(mockUserJpa)).thenReturn(mockUserJpa);

        UserJpa result = userServiceComplete.updateUserJpa(1L, mockUserJpa);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userJpaRepository, times(1)).save(mockUserJpa);
    }

    @Test
    public void testDeleteMongoUser_Success() {
        String userId = "userId";

        userServiceComplete.deleteMongoUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserJpa_Success() {
        Long userId = 1L;

        userServiceComplete.deleteUserJpa(userId);

        verify(userJpaRepository, times(1)).deleteById(userId);
    }
}
